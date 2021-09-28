package com.epam.quiz.service.impl;

import com.epam.quiz.entity.Result;
import com.epam.quiz.repository.ResultRepository;
import com.epam.quiz.service.interfaces.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResultServiceImpl implements ResultService {
    @Autowired
    ResultRepository resultRepository;


    @Override
    public Double getUserAverageScoreById(Long id) {
        return resultRepository.getUserAvgScoreById(id);
    }

    public List<Result> getUserResultsPageByEmail(String email, Pageable pageable){
        return resultRepository.getUserResultsPageByEmail(email, pageable);
    }
    @Override
    public List<Result> getUserResultsById(Long id) {
        return resultRepository.findAllById(id);
    }

    @Override
    public Optional<Result> getQuizResultById(Long id) {
        return resultRepository.findById(id);
    }

    @Override
    public boolean createResult(Result result) {
        resultRepository.save(result);
        return true;
    }

    @Override
    public Long getUserResultsCountByEmail(String email ) {
        return resultRepository.countById(email);
    }
}
