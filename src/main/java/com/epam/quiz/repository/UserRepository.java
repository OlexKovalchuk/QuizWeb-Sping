package com.epam.quiz.repository;

import com.epam.quiz.dto.UserDTO;
import com.epam.quiz.entity.Role;
import com.epam.quiz.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false " +
            "END FROM User u WHERE u.email = :email")
    boolean isUserExistByEmail(@Param("email") String email);

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("Update User u set u.name=:name, u.surname=:surname,u.role=:role where u.id=:id")
    void updateUserInfo(@Param("id") Long id, @Param("name") String name, @Param("surname") String surname, @Param(
            "role") Role role);

    @Modifying
    @Query("Update User u set u.name=:name, u.surname=:surname where u" +
            ".email=:oldEmail")
    void updateUserPersonalInfo(@Param("name") String name, @Param("surname") String surname,
                                @Param("oldEmail") String oldEmail);

    @Modifying
    @Query("Update User u set u.block=:block where u.id=:id")
    void blockUser(@Param("id") Long id, @Param("block") int block);

    @Query("select u.block From User u where u.id=:id")
    int getUserBlockById(@Param("id") Long id);

    Optional<User> findUserById(Long id);

    @Modifying
    @Query("update User u set u.email=:email where u.email=:oldEmail")
    void updateUserEmail(@Param("email") String email, @Param("oldEmail") String oldEmail);

    @Modifying
    @Query("update User u set u.password=:password where u.email=:email")
    void updateUserPassword(@Param("password") String password, @Param("email") String email);
@Query("SELECT u from User u where u.email!=:email")
    List<User> findAllUsers(Pageable pageable,@Param("email") String email);
}