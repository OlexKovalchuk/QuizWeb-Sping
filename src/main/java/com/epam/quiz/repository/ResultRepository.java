package com.epam.quiz.repository;

import com.epam.quiz.entity.Result;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result,Long> {
    @Query("SELECT AVG(r.score) FROM Result  r where r.user.id = :id")
    Double getUserAvgScoreById(@Param("id") Long id);
    @Query("SELECT r FROM Result r where r.user.id = :id")
    List<Result> findAllById( @Param("id")  Long id);
    @Query("SELECT COUNT(r) FROM Result r join User u on(r.user=u) where u.email =:email")
    Long countById(@Param ("email")String email);

    @Query("SELECT r From Result r join Quiz q on(r.quiz=q) join User u on(r.user=u) where u.email =:email")
    List<Result> getUserResultsPageByEmail(@Param("email" )String email, Pageable pageable);
}