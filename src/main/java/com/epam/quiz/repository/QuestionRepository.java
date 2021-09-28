package com.epam.quiz.repository;

import com.epam.quiz.entity.Question;
import com.epam.quiz.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question,Long> {
    @Query("SELECT q From Question q where q.quiz.id = :id")
    List<Question> getAllQuizQuestions(@Param("id")Long id);
    @Modifying
@Query(nativeQuery = true ,value = "Delete from mydb.question where quiz_id=:id")
    void deleteQuestionsByQuizId(@Param("id") Long id);
}