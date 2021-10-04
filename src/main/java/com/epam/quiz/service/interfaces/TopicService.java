package com.epam.quiz.service.interfaces;

import com.epam.quiz.entity.Topic;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    boolean create(Topic topic);
    List<Topic> findAll();
     Optional<Topic> findById(String id);
}