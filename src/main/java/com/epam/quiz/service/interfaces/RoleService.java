package com.epam.quiz.service.interfaces;

import com.epam.quiz.entity.Role;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAllRoles();
    Optional<Role> getUserRoleById(Long id);
    boolean create(Role role);
}