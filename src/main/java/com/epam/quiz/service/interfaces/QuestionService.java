package com.epam.quiz.service.interfaces;

import com.epam.quiz.entity.Question;

import java.util.List;

public interface QuestionService {
    boolean create(Question question);
    List<Question> getAllQuizQuestions(Long id);
     void saveQuestionForQuiz(List<Question> questions);
     void deleteQuizQuestionsByQuizId(Long id);
}