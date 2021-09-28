package com.epam.quiz.entity;

import com.epam.quiz.dto.UserDTO;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@Table(name = "user")
public class User implements Serializable, Cloneable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "block")
    private int block;
    @Column(name = "create_date")
    private Date createDate;
    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;
    @OneToMany
    @JoinColumn(name = "user_id")
    private List<Result> results;


    public String getAverageScore() {
        if (results.isEmpty()) {
            return "0";
        }
        float sum = 0;
        for (Result result : results) {
            sum += result.getScore();
        }
        DecimalFormat format = new DecimalFormat("##.00");
        return format.format(sum / results.size());
    }

    public static User of(Long id, String email, String password, String name, String surname, Role role,
                          Date createDate, int block) {
        return User.builder()
                .id(id)
                .email(email)
                .password(password)
                .name(name)
                .surname(surname)
                .role(role)
                .createDate(createDate)
                .block(block)
                .build();
    }

    public static User fromDTO(UserDTO userDTO) {
        return User.of(userDTO.getId(), userDTO.getEmail(), userDTO.getPassword(), userDTO.getName(),
                userDTO.getSurname(), userDTO.getRole(), userDTO.getCreateDate(), userDTO.getBlock());
    }

    public UserDTO toDTO() {
        return UserDTO.of(id, email, null, name, surname, role, createDate, block);
    }
}