package com.epam.quiz.service.impl;

import com.epam.quiz.dto.QuizDTO;
import com.epam.quiz.entity.Quiz;
import com.epam.quiz.entity.Topic;
import com.epam.quiz.repository.QuizRepository;
import com.epam.quiz.repository.TopicRepository;
import com.epam.quiz.service.interfaces.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {
    final
    QuizRepository quizRepository;
    final
    TopicRepository topicRepository;

    public QuizServiceImpl(QuizRepository quizRepository, TopicRepository topicRepository) {
        this.quizRepository = quizRepository;
        this.topicRepository = topicRepository;
    }

    @Override
    public Optional<Quiz> getQuizById(String id) {
        return quizRepository.findById(Long.parseLong(id));
    }

    @Override
    public boolean createQuiz(Quiz quiz) {
        quiz.setCreateDate(new Date(Calendar.getInstance().getTime().getTime()));
        quizRepository.save(quiz);
        return true;
    }

    @Transactional
    @Override
    public void editQuizInfo(QuizDTO quiz) {
        quizRepository.editQuizInfo(quiz.getId(), quiz.getHeader(), quiz.getDescription(), quiz.getDifficult(),
                quiz.getDuration(), quiz.getTopic());
    }

    @Transactional(readOnly = true)
    @Override
    public List<Quiz> getQuizzesByPage(Pageable pageable, Long topicSort) {
        List<Quiz> quiz = quizRepository.getAllQuizzesByPage(pageable,
                (long) topicSort).getContent();

        return quiz;
    }

    @Transactional
    @Override
    public boolean deleteQuiz(Long id) {
        if (quizRepository.isQuizExistById(id)) {
            if (quizRepository.isAnyPassedQuiz(id)) {
                quizRepository.archiveQuiz(id);
            } else {
                quizRepository.deleteQuizById(id);
            }
            return true;
        }
        return false;
    }

    @Override
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    @Override
    public Long getQuizzesCount() {
        return quizRepository.getQuizzesCount();
    }
}