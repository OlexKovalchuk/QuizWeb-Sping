package com.epam.quiz.repository;

import com.epam.quiz.entity.Quiz;
import com.epam.quiz.entity.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface QuizRepository extends JpaRepository<Quiz, Long> {
    @Query("SELECT CASE WHEN COUNT(q) > 0 THEN true ELSE false " +
            "END FROM Quiz q WHERE q.id = :id")
    boolean isQuizExistById(@Param("id") Long id);

    @Modifying
    @Query("delete from Quiz q where q.id =:id")
    void deleteQuizById(@Param("id") Long id);

    @Query("select  count(q) from Quiz  q where q.archived=0")
    Long getQuizzesCount();

    @Modifying
    @Query("Update Quiz q set q.description= :description ,q.header= :header, q.difficult = :difficult,q" +
            ".duration= :duration, q.topic= :topic where q.id= :id and q.id>0")
    void editQuizInfo(@Param("id") Long id, @Param("header") String header,
                      @Param("description") String description, @Param("difficult") String difficult, @Param(
            "duration") int duration, @Param("topic") Topic topic);

    @Query(value = "SELECT q From Quiz q where (q.topic.id=:topicId or 0=:topicId) and q.topic.archived=0 and q.archived=0 ")
    Page<Quiz> getAllQuizzesByPage(Pageable pageable, @Param("topicId") Long topicId);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false " +
            "END FROM Result r WHERE r.quiz.id = :id")
    boolean isAnyPassedQuiz(@Param("id") Long id);

    @Modifying
    @Query("UPDATE  Quiz q set q.archived=1 where q.id=:id")
    void archiveQuiz(@Param("id") Long id);
}