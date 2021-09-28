package com.epam.quiz.service.interfaces;

import com.epam.quiz.entity.Topic;

import java.util.List;

public interface TopicService {
    boolean create(Topic topic);
    List<Topic> findAll();
}
