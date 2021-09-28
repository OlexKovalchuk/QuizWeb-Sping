package com.epam.quiz.service.impl;

import com.epam.quiz.dto.UserDTO;
import com.epam.quiz.entity.User;
import com.epam.quiz.repository.UserRepository;
import com.epam.quiz.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void updateUserPersonalInfo(UserDTO user) {
        userRepository.updateUserPersonalInfo(user.getName(), user.getSurname(),user.getEmail());
    }
    @Transactional
    public void updateUserEmail(UserDTO user,String email) {
        userRepository.updateUserEmail(user.getEmail(),email);
    }
    @Transactional
    public void updateUserPassword(UserDTO user) {
        userRepository.updateUserPassword(user.getPassword(),user.getEmail());
    }
    @Transactional
    public void updateUserInfo(UserDTO user) {
        userRepository.updateUserInfo(user.getId(),user.getName(), user.getSurname(), user.getRole());
    }

    public boolean isUserExistByEmail(String email) {
        return userRepository.isUserExistByEmail(email);
    }
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public Optional<User> findUserById(Long id) {
        return userRepository.findUserById(id);
    }
    @Transactional
    public void blockUser(Long id) {
         userRepository.blockUser(id,userRepository.getUserBlockById(id)^1);
    }
    //
//    public User saveUser(User user) {
//        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        user.setBlock(0);
//        Role userRole = roleRepository.findByRole("ADMIN");
//        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
//        return userRepository.save(user);
//    }
    //@Override
    public boolean create(UserDTO user) {
        if (!userRepository.isUserExistByEmail(user.getEmail())) {
            user.setCreateDate(new Date(Calendar.getInstance().getTime().getTime()));
            userRepository.save(User.fromDTO(user));
            return true;
        }
        return false;
    }

//    @Override
//    public boolean isPasswordCorrect(UserDTO user) {
//        if(user.getPassword().equals(userRepository.findByEmail(user.getEmail()).get().getPassword())){
//            return true;
//        }
//        return false;
//    }
//
//    @Transactional
//    @Override
//    public boolean update(User user) {
//        userRepository.save(user);
//        return true;
//    }


    @Override
    public List<User> findAll(Pageable pageable) {
        return userRepository.findAll();
    }

    @Override
    public Long getUsersCount() {
        return userRepository.count();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}