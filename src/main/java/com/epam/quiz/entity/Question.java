package com.epam.quiz.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Table(name = "question")
public class Question implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    @ToString.Exclude
    private List<Answer> answers = new ArrayList<>();

    public boolean isCorrect(List<Integer>answersList) {
        System.out.println(answersList);
        for (int i = 0; i < answers.size(); i++) {
            if ((answers.get(i).getAnswer() == 1 && !answersList.contains(i)) || (answers.get(i).getAnswer() == 0 && answersList.contains(i))) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}