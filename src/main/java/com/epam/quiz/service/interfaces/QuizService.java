package com.epam.quiz.service.interfaces;

import com.epam.quiz.entity.Quiz;

import java.util.List;

public interface QuizService {

    boolean createQuiz(Quiz quiz);

    boolean deleteQuiz(Long id);
    List<Quiz> getAllQuizzes();

    Long getQuizzesCount();
}
