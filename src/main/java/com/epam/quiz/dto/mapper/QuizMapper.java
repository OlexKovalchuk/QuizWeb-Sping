package com.epam.quiz.dto.mapper;

import com.epam.quiz.dto.QuizDTO;
import com.epam.quiz.entity.Quiz;

public class QuizMapper  {
    public static QuizDTO mapToDto(Quiz quiz) {
        QuizDTO dto = new QuizDTO();
        dto.setId(quiz.getId());
        dto.setDescription(quiz.getDescription());
        dto.setHeader(quiz.getHeader());
        dto.setDifficult(quiz.getDifficult());
        dto.setTopic(quiz.getTopic());
    dto.setDuration(quiz.getDuration());
    dto.setCreateDate(quiz.getCreateDate());
    return dto;
    }

    public static Quiz mapFromDto(QuizDTO dto) {
        Quiz quiz = Quiz.builder()
                .description(dto.getDescription())
                .topic(dto.getTopic())
                .header(dto.getHeader())
                .difficult(dto.getDifficult())
                .duration(dto.getDuration())
                .build();
        return quiz;
    }
}