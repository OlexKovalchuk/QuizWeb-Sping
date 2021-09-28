package com.epam.quiz.repository;

import com.epam.quiz.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository  extends JpaRepository<Role,Long> {
    @Query("SELECT r FROM Role  r where r.name = :name")
    Optional<Role> findByName(@Param("name") String name);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false " +
            "END FROM Role r WHERE r.name = :name")
    boolean isRoleExistsByName(@Param("name") String name);
    @Query("SELECT r.name FROM Role  r where r.id = :id")
    String getRoleNameById(int id);
}