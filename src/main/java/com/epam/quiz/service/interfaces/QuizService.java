package com.epam.quiz.service.interfaces;

import com.epam.quiz.dto.QuizDTO;
import com.epam.quiz.entity.Quiz;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface QuizService {

    boolean createQuiz(Quiz quiz);
     Optional<Quiz> getQuizById(String id);
    boolean deleteQuiz(Long id);
    List<Quiz> getAllQuizzes();
     void editQuizInfo(QuizDTO quiz);
     List<Quiz> getQuizzesByPage(Pageable pageable, Long topicSort);
    Long getQuizzesCount();
}