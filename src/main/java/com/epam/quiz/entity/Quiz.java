package com.epam.quiz.entity;

import com.epam.quiz.dto.QuizDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@Table(name = "quiz")
public class Quiz implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "header")
    private String header;

    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "topic_id")
    private Topic topic;

    @OneToMany(cascade = CascadeType.ALL,  orphanRemoval = true)
    @JoinColumn(name = "quiz_id")
    private List<Question> questions = new ArrayList<>();

    public void addQuestion(Question question) {
        questions.add(question);
        question.setQuiz(this);
    }
    public void removeQuestion(Question question) {
        questions.remove(question);
        question.setQuiz(null);
    }

    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "duration")
    private int duration;
    @Column(name = "difficult")
    private String difficult;


    public static Quiz of(Long id, String header, String description, int duration, Date createDate,
                          String difficult, List<Question> questions, Topic topic) {
        return Quiz.builder()
                .id(id)
                .header(header)
                .description(description)
                .duration(duration)
                .createDate(createDate)
                .difficult(difficult)
                .questions(questions)
                .topic(topic)
                .build();
    }

    public Quiz fromDTO(QuizDTO quizDTO) {
        return Quiz.of(quizDTO.getId(), quizDTO.getHeader(), quizDTO.getDescription(), quizDTO.getDuration(),
                quizDTO.getCreateDate(), quizDTO.getDifficult(), quizDTO.getQuestions(), quizDTO.getTopic());
    }

    public QuizDTO toDTO() {
        return QuizDTO.of(id, header, description, duration, createDate, difficult, questions, topic);
    }
}