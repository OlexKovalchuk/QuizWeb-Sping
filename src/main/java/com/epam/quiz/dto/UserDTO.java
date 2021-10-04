package com.epam.quiz.dto;


import com.epam.quiz.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.sql.Date;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @Email
    private String email;

    private String password;

    private String name;
    private String surname;
    private Date createDate;
    private int block;
    private Role role;


}