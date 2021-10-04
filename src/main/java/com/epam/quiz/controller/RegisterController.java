package com.epam.quiz.controller;

import com.epam.quiz.dto.UserDTO;
import com.epam.quiz.entity.Quiz;
import com.epam.quiz.entity.Role;
import com.epam.quiz.entity.User;
import com.epam.quiz.service.impl.QuizServiceImpl;
import com.epam.quiz.service.impl.RoleServiceImpl;
import com.epam.quiz.service.impl.UserServiceImpl;
import com.epam.quiz.service.interfaces.RoleService;
import com.epam.quiz.util.Pages;
import com.epam.quiz.util.WebPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class RegisterController {
    final
    UserServiceImpl userService;

    final
    RoleServiceImpl roleService;


    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public RegisterController(UserServiceImpl userService, RoleServiceImpl roleService,
                              AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping(value = "/register")
    public String getRegisterPage() {
        return Pages.REGISTER;
    }

    @PostMapping(value = "/register")
    public ModelAndView signUp(HttpServletRequest request, @Valid UserDTO user, Model model) {
        user.setRole(new Role("USER",2L));

        String password = user.getPassword();
        String passHash = passwordEncoder.encode(user.getPassword());
        user.setPassword(passHash);

        if (!userService.create(user)) {
            model.addAttribute("error_user", true);
            return new ModelAndView("redirect:/register?error");
        }
        authWithAuthManager(request, user.getEmail(), password);
         return new ModelAndView(WebPath.REDIRECT+WebPath.HOME);
    }

    @GetMapping(value = "/login")
    public String getLogin() {
        return Pages.LOGIN;
    }


    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}