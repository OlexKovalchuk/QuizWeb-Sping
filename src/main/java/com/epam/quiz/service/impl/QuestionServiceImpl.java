package com.epam.quiz.service.impl;

import com.epam.quiz.entity.Question;
import com.epam.quiz.entity.Quiz;
import com.epam.quiz.repository.QuestionRepository;
import com.epam.quiz.service.interfaces.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    QuestionRepository questionRepository;

    @Override
    public boolean create(Question question) {
        questionRepository.save(question);
        return true;
    }

    @Override
    public List<Question> getAllQuizQuestions(Long id) {
        return questionRepository.getAllQuizQuestions(id);
    }
@Transactional
    public void deleteQuizQuestionsByQuizId(Quiz quiz) {
        questionRepository.deleteQuestionsByQuizId(quiz.getId());
    }

    public void saveQuestionForQuiz(List<Question> questions) {
        questionRepository.saveAll(questions);

    }
}