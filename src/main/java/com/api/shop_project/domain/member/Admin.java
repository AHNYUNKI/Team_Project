package com.api.shop_project.domain.member;

import javax.persistence.*;

@Entity
public class Admin {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private final Role role = Role.ADMIN;
}
