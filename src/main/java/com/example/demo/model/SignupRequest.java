package com.example.demo.model;

import lombok.Data;

@Data
public class SignupRequest {

    private String firstName;

    private String lastName;

    private String username;

    private String password;

    private String tel;

    private String address;
}