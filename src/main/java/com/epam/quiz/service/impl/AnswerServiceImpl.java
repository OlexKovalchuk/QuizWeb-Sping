package com.epam.quiz.service.impl;

import com.epam.quiz.entity.Answer;
import com.epam.quiz.repository.AnswerRepository;
import com.epam.quiz.service.interfaces.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    final
    AnswerRepository answerRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }


    @Override
    public boolean create(Answer answer) {
        answerRepository.save(answer);
        return true;
    }

    @Override
    public List<Answer> getAllAnswersByQuestionId(Long id) {
        return answerRepository.findAllById(id);
    }
}