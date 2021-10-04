package com.epam.quiz.controller;

import com.epam.quiz.dto.UserDTO;
import com.epam.quiz.entity.User;
import com.epam.quiz.service.impl.ResultServiceImpl;
import com.epam.quiz.service.impl.UserServiceImpl;
import com.epam.quiz.util.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {
    final
    UserServiceImpl userService;
    final
    ResultServiceImpl resultService;

    public AdminController(UserServiceImpl userService, ResultServiceImpl resultService) {
        this.userService = userService;
        this.resultService = resultService;
    }

    @GetMapping("/users")
    String getUsers(@RequestParam(defaultValue = "createDate") String sort, @RequestParam(defaultValue =
            "1") int page, Model model, HttpServletRequest request) {
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        if (request.getParameter("block") != null) {
            userService.blockUser(Long.parseLong(request.getParameter("block")));
        }
        request.setAttribute("sort", sort);
        request.setAttribute("page", page);

        model.addAttribute("pagesCount", Math.ceil((userService.getUsersCount() - 1) / 10.0));
        model.addAttribute("users", userService.findAll(PageRequest.of(
         page - 1, 10, Sort.by(sort).ascending()),email));
        return Pages.USER_LIST;
    }

    @GetMapping("/users/edit")
    String getUserInfoPage(Model model, @RequestParam("id") Long id) {
        User user = userService.findUserById(id).get();
        model.addAttribute("user", user);
        return Pages.EDIT_USER;
    }

    @PostMapping("/users/edit")
    String editUserInfo(Model model, @Valid UserDTO user) {
        userService.updateUserInfo(user);
        model.addAttribute("user", userService.findUserById(user.getId()).get());
        return Pages.EDIT_USER;
    }

    @PostMapping("/users/delete")
    String deleteUser(Model model, @RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin/users";
    }

}