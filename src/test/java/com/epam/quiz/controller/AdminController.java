package com.epam.quiz.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithUserDetails("admin@mail.com")
@TestPropertySource("/application-test.properties")
public class AdminController {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    AdminController adminController;

    @Test
    @WithUserDetails("admin@mail.com")
    public void deleteUserTest() throws Exception {
        this.mockMvc.perform(post("/admin/users/delete").param("id", "13")).andDo(print()).andExpect(status().is3xxRedirection()).andExpect(redirectedUrl("/admin/users"));
    }
}