package com.softuni.bettleship_exam.model.dtos;

import javax.validation.constraints.NotBlank;

public class UserLoginDTO {

    private Long id;

    @NotBlank
    private String username;
    @NotBlank
    private String password;

    public Long getId() {
        return id;
    }

    public UserLoginDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserLoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
