package com.epam.quiz.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "question")
public class Question implements Serializable, Cloneable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "question_id")
    private List<Answer> answers = new ArrayList<>();

    public boolean isCorrect(String[] answersArray) {
        List<String> answersList = Arrays.asList(answersArray);
        System.out.println(answersList);
        for (int i = 0; i < answers.size(); i++) {
            if ((answers.get(i).getAnswer() == 1 && !answersList.contains(Integer.toString(i))) || (answers.get(i).getAnswer() == 0 && answersList.contains(Integer.toString(i)))) {
                return false;
            }
        }
        return true;
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
        answer.setQuestion(this);
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
        answer.setQuestion(null);
    }

    public Question(String description, List<Answer> answers) {
        this.description = description;
        this.answers = answers;
    }
}