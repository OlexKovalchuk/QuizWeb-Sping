package com.epam.quiz.dto.mapper;

import com.epam.quiz.dto.UserDTO;
import com.epam.quiz.entity.User;

public class UserMapper {

    public static UserDTO mapToDto(User user) {
        UserDTO dto = new UserDTO();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setName(user.getName());
        dto.setId(user.getId());
        dto.setSurname(user.getSurname());
        dto.setRole(user.getRole());
        return dto;
    }


    public static User mapFromDto(UserDTO userDTO) {
        return User.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .role(userDTO.getRole())
                .createDate(userDTO.getCreateDate())
                .block(userDTO.getBlock())
                .build();
    }
}