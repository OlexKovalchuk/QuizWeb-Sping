package com.epam.quiz.service.impl;

import com.epam.quiz.entity.Role;
import com.epam.quiz.repository.RoleRepository;
import com.epam.quiz.service.interfaces.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Optional<Role> getUserRoleById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public boolean create(Role role) {
        if (roleRepository.isRoleExistsByName(role.getName())) {
            roleRepository.save(role);
            return true;
        }
        return false;
    }

    public Optional<Role> findUserRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}