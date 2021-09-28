package com.epam.quiz.service.interfaces;

import com.epam.quiz.entity.Result;

import java.util.List;
import java.util.Optional;

public interface ResultService {
    Double getUserAverageScoreById(Long id);

    List<Result> getUserResultsById(Long id);

    Optional<Result> getQuizResultById(Long id);

    boolean createResult(Result result);

    Long getUserResultsCountByEmail(String email);
}
