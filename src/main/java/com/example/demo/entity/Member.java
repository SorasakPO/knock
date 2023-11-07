package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Data
@Entity
public class Member {
    @Id
    @GeneratedValue
    private UUID userId;

    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String tel;
    private String address;
    private String role;
}
