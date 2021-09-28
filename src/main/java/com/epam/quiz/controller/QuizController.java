package com.epam.quiz.controller;

import com.epam.quiz.dto.QuizDTO;
import com.epam.quiz.entity.Answer;
import com.epam.quiz.entity.Question;
import com.epam.quiz.entity.Quiz;
import com.epam.quiz.entity.Result;
import com.epam.quiz.service.impl.*;
import com.epam.quiz.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping(value = "/quiz")
public class QuizController {
    @Autowired
    private TopicServiceImpl topicService;
    @Autowired
    private QuestionServiceImpl questionService;
    @Autowired
    private ResultServiceImpl resultService;
    @Autowired
    private QuizServiceImpl quizService;
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/create")
    public String getQuizCreatePage(Model model) {
        model.addAttribute("topics", topicService.findAll());
        return Pages.QUIZ_CREATE;
    }

    @PostMapping("/create")
    public ModelAndView createQuiz(Model model, HttpServletRequest request,
                                   @RequestParam Map<String, Object> allRequestParams) {
        Quiz quiz = new Quiz();
        Enumeration<String> params = request.getParameterNames();
        quiz.setHeader(request.getParameter(params.nextElement()));
        quiz.setTopic(topicService.findById(request.getParameter(params.nextElement())).get());
        quiz.setDifficult(request.getParameter(params.nextElement()));
        quiz.setDuration(Integer.parseInt(request.getParameter(params.nextElement())));
        quiz.setDescription(request.getParameter(params.nextElement()));
        String param = params.nextElement();
        while (params.hasMoreElements()) {
            if (param.charAt(0) == 'd') {
                Question question = new Question();
                question.setDescription(request.getParameter(param));
                List<String> getParameter = null;
                while (params.hasMoreElements()) {
                    param = params.nextElement();
                    if (param.charAt(0) != 'd') {
                        Answer answer = new Answer();
                        answer.setAnswer(0);
                        if (param.charAt(0) == 'q') {
                            getParameter = Arrays.asList(request.getParameterValues(param));
                            answer.setAnswer(1);
                            param = params.nextElement();
                        }
                        if (getParameter != null && getParameter.contains(param.substring(7))) {
                            answer.setAnswer(1);
                        }
                        answer.setDescription(request.getParameter(param));
                        question.addAnswer(answer);
                    } else {
                        break;
                    }
                }
                quiz.addQuestion(question);
            }
        }
        quizService.createQuiz(quiz);
        return new ModelAndView("redirect:/home/list");
    }

    @GetMapping("/edit")
    public String getQuizEditPage(Model model, @RequestParam("id") String id) {
        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("quiz", quizService.getQuizById(id).get());
        return Pages.QUIZ_EDIT;
    }

    @PostMapping("/editInfo")
    public String editQuizInfo(Model model, @Valid QuizDTO quiz) {
        quizService.editQuizInfo(quiz);
        return getQuizEditPage(model, Long.toString(quiz.getId()));
    }

    @PostMapping("/editQuestions")
    public String editQuizQuestions(Model model, HttpServletRequest request) {
        List<Question> questions = new ArrayList<>();
        Enumeration<String> params = request.getParameterNames();
        String param = params.nextElement();
        int id = Integer.parseInt(request.getParameter(param));
        Quiz quiz = quizService.getQuizById(Long.toString(id)).get();
        param = params.nextElement();
        while (params.hasMoreElements()) {
            if (param.charAt(0) == 'd') {
                Question question = new Question();
                question.setQuiz(quiz);
                question.setDescription(request.getParameter(param));
                List<String> getParameter = null;
                while (params.hasMoreElements()) {
                    param = params.nextElement();
                    if (param.charAt(0) != 'd') {
                        Answer answer = new Answer();
                        answer.setAnswer(0);
                        if (param.charAt(0) == 'q') {
                            getParameter = Arrays.asList(request.getParameterValues(param));
                            answer.setAnswer(1);
                            param = params.nextElement();
                        }
                        if (getParameter != null && getParameter.contains(param.substring(7))) {
                            answer.setAnswer(1);
                        }
                        answer.setDescription(request.getParameter(param));
                        question.addAnswer(answer);
                    } else {
                        break;
                    }
                }
                questions.add(question);
            }
        }
        questionService.deleteQuizQuestionsByQuizId(quiz);
        questionService.saveQuestionForQuiz(questions);
        return "redirect:/home/list";
    }

    @PostMapping("/start")
    public String getQuizStartPage(Model model, @RequestParam("id") String id) {
        model.addAttribute("quiz", quizService.getQuizById(id).get());
        return Pages.QUIZ_START;
    }

    @PostMapping("/finish")
    public String finishQuiz(Model model, @RequestParam("id") String id, HttpServletRequest request) {
        Quiz quiz = quizService.getQuizById(id).get();
        int score;
        double correct = 0;
        List<Question> questionList = quiz.getQuestions();
        for (int i = 0; i < questionList.size(); i++) {
            if (request.getParameterValues("question-" + i + "-answers") != null) {
                if (questionList.get(i).isCorrect(request.getParameterValues("question-" + i + "-answers"))) {
                    correct++;
                }
            }
        }
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        score = (int) (100 * correct / questionList.size());
        Result result =
                Result.builder().quiz(quiz).passedDate(new Timestamp(System.currentTimeMillis())).score(score).user(userService.findUserByEmail(email).get()).build();
        resultService.createResult(result);
        return "redirect:/home/list";
    }
}