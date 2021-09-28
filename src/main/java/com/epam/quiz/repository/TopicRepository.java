package com.epam.quiz.repository;

import com.epam.quiz.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository  extends JpaRepository<Topic,Long> {
    @Query("SELECT CASE WHEN COUNT(t) > 0 THEN true ELSE false " +
            "END FROM Topic t WHERE t.name = :name")
    boolean isTopicExistByName(@Param("name") String name);
    @Query("SELECT t from Topic t where t.id>0")
    List<Topic> findAllTopics();
}