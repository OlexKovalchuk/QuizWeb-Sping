package com.epam.quiz.controller;

import com.epam.quiz.dto.UserDTO;
import com.epam.quiz.entity.User;
import com.epam.quiz.service.impl.ResultServiceImpl;
import com.epam.quiz.service.impl.UserServiceImpl;
import com.epam.quiz.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
public class UserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    ResultServiceImpl resultService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    String getProfilePage() {
        return Pages.PROFILE_PAGE;
    }

    @Autowired
    private AuthenticationManager authenticationManager;


    @GetMapping("/edit")
    String getPersonalInfoPage(Model model) {
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        User user = userService.findUserByEmail(email).get();
        model.addAttribute("user", user);
        return Pages.USER_INFO;
    }

    @PostMapping("/editRegalia")
    String editUserRegalia(HttpServletRequest request, @Valid UserDTO user, Model model) {
        Object principal;
        principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setEmail(((UserDetails) principal).getUsername());
        userService.updateUserPersonalInfo(user);
        return "redirect:/profile/edit";
    }
    @GetMapping("/results")
    String getUserResults(@RequestParam(defaultValue = "passedDate") String sort, @RequestParam(defaultValue =
            "1") String page, Model model, HttpServletRequest request) {
        request.setAttribute("sort", sort);
        request.setAttribute("page", Integer.parseInt(page));
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        model.addAttribute("pagesCount", Math.ceil(resultService.getUserResultsCountByEmail(email) / 5.0));
        model.addAttribute("userResults", resultService.getUserResultsPageByEmail(email,
                PageRequest.of(Integer.parseInt(page) - 1, 5, Sort.by(sort).ascending())));
        return Pages.USER_RESULTS;
    }
    @PostMapping("/edit")
    String editUserInfo(HttpServletRequest request, @Valid UserDTO user, Model model) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = ((UserDetails) principal).getUsername();
        String password = null;
        if (!user.getEmail().isEmpty()) {
            if (userService.isUserExistByEmail(user.getEmail())) {
                model.addAttribute("error", "This email has already been registered");
                return getPersonalInfoPage(model);
            } else {
                userService.updateUserEmail(user, email);
            }
        }
        if (!user.getPassword().isEmpty()) {
            password = user.getPassword();
            user.setPassword(passwordEncoder.encode(password));
            userService.updateUserPassword(user);
        }
        if (!user.getPassword().isEmpty() && !user.getEmail().isEmpty()) {
            authWithAuthManager(request, user.getEmail(), password);
        } else if (!user.getPassword().isEmpty() || !user.getEmail().isEmpty()) {
            return "redirect:/logout";
        }

        return "redirect:/profile/edit";
    }

    public void authWithAuthManager(HttpServletRequest request, String username, String password) {
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
        authToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authentication = authenticationManager.authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }




}