package com.epam.quiz.service.interfaces;

import com.epam.quiz.dto.UserDTO;
import com.epam.quiz.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {


    List<User> findAll(Pageable pageable, String email);

    void deleteUser(Long id);

    Long getUsersCount();

    boolean create(UserDTO user);

    void blockUser(Long id);

    Optional<User> findUserById(Long id);

    Optional<User> findUserByEmail(String email);

    boolean isUserExistByEmail(String email);

    void updateUserInfo(UserDTO user);

    void updateUserPassword(UserDTO user);

    void updateUserEmail(UserDTO user, String email);

    void updateUserPersonalInfo(UserDTO user);

}