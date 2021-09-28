package com.epam.quiz.controller;

import com.epam.quiz.entity.Quiz;
import com.epam.quiz.service.impl.QuizServiceImpl;
import com.epam.quiz.service.interfaces.TopicService;
import com.epam.quiz.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    @Autowired
    private QuizServiceImpl quizService;
    @Autowired
    private TopicService topicService;


    @GetMapping("/list")
    public String getAllQuizzes(@RequestParam(defaultValue = "questions.size") String sort,
                                @RequestParam(defaultValue = "0") Long topicId, @RequestParam(defaultValue =
            "1") String page, Model model, HttpServletRequest request) {
        request.setAttribute("sort", sort);
        request.setAttribute("page", Integer.parseInt(page));

        List<Quiz> quizzes = quizService.getQuizzesByPage(PageRequest.of(Integer.parseInt(page) - 1, 4,
                Sort.by(sort).ascending()), topicId);
        model.addAttribute("quizzes", quizzes);
        model.addAttribute("topicId", topicId);

        model.addAttribute("topics", topicService.findAll());
        model.addAttribute("pagesCount", Math.ceil(quizService.getQuizzesCount() / 4.0));
        return Pages.HOME;
    }

    @PostMapping("/delete")
    public String deleteQuiz(@RequestParam("id") Long id) {
        quizService.deleteQuiz(id);
        return "redirect:/home/list";
    }
}