package com.epam.quiz.dto;

import com.epam.quiz.entity.Question;
import com.epam.quiz.entity.Topic;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuizDTO {

    private Long id;
    private String header;
    private String description;
    private int duration;
    private Date createDate;
    private String difficult;
    private List<Question> questions;
    private Topic topic;

}