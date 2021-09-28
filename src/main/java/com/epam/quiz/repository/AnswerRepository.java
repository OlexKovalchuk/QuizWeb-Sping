package com.epam.quiz.repository;

import com.epam.quiz.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnswerRepository extends JpaRepository<Answer,Long> {

    @Query("SELECT a From Answer a where a.question.id = :id")
    List<Answer> findAllById(@Param("id") Long id);
}

