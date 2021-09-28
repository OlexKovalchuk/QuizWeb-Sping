package com.epam.quiz.service.impl;

import com.epam.quiz.entity.Topic;
import com.epam.quiz.repository.TopicRepository;
import com.epam.quiz.service.interfaces.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    TopicRepository topicRepository;


    @Override
    public boolean create(Topic topic) {
        if (topicRepository.isTopicExistByName(topic.getName())) {
            topicRepository.save(topic);
            return true;
        }
        return false;
    }
    public Optional<Topic>findById(String id){
        return topicRepository.findById(Long.parseLong(id));
    }
    @Override
    public List<Topic> findAll() {
        return topicRepository.findAllTopics();
    }
}