package com.epam.quiz.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name="role")
@Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;

    public Role(String name, Long id) {
        this.name = name;
        this.id = id;
    }

    public Role() {
    }

//    public enum RoleEnum{
//        USER("USER",2L),ADMIN("ADMIN",1L);
//        RoleEnum(String name, Long id){
//            this.name = name;
//            this.id = id;
//        }
//        String name;
//        Long id ;
//    }
}