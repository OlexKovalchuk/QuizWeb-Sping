package com.epam.quiz.service.interfaces;

import com.epam.quiz.entity.Answer;

import java.util.List;

public interface AnswerService {

    boolean create(Answer answer);
    List<Answer> getAllQuestionAnswersById(Long id);
}
