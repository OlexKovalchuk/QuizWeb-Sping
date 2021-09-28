package com.epam.quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "result")
public class Result implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private Long id;

    @Column(name = "score")
    private int score;

    @ManyToOne(optional = false, cascade =  CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "passed_date")
    private Timestamp passedDate;
    @ManyToOne(optional = false,cascade =  CascadeType.ALL)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


}