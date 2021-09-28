package com.epam.quiz.service.interfaces;

import com.epam.quiz.entity.User;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
//    boolean create(UserDTO user);
//
//    boolean isPasswordCorrect(UserDTO user);
//    boolean update(User user);
//
//    Optional<User> findUserByEmail(String email);
//
    List<User> findAll(Pageable pageable);

    Long getUsersCount();
}
