package com.epam.quiz.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Test
    public void getAllUsers() throws Exception {
        this.mockMvc.perform(get("/admin/users")
                        .with(user("admin@mail.com")
                                .password("12345678")
                                .roles("ADMIN")))
                .andExpect(status().isOk())
        .andExpect(content().string(containsString("Users list")));
    }


    @Test
    public void getEditQuizPage() throws Exception {
        this.mockMvc.perform(get("/quiz/edit")
                        .with(user("admin@mail.com")
                                .password("12345678")
                                .roles("ADMIN"))
                        .param("id","1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Quiz edit")));

    }
    @Test
    public void getQuizCreatePage() throws Exception {
        this.mockMvc.perform(get("/quiz/create")
                        .with(user("admin@mail.com")
                                .password("12345678")
                                .roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Quiz create")));

    }
    @Test
    public void getUserEditPage() throws Exception {
        this.mockMvc.perform(get("/admin/users/edit")
                        .param("id","21")
                        .with(user("admin@mail.com")
                                .password("12345678")
                                .roles("ADMIN")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Edit user")));

    }

}