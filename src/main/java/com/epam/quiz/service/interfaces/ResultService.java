package com.epam.quiz.service.interfaces;

import com.epam.quiz.entity.Result;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ResultService {
    Double getUserAverageScoreById(Long id);

    List<Result> getUserResultsById(Long id);
     List<Result> getUserResultsPageByEmail(String email, Pageable pageable);
    Optional<Result> getQuizResultById(Long id);

    boolean createResult(Result result);

    Long getUserResultsCountByEmail(String email);
}