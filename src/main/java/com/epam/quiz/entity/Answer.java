package com.epam.quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@Data
@AllArgsConstructor
@Table(name = "answer")
public class Answer implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "answer")
    private int answer;
    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;

}
