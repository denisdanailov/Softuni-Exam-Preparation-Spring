package com.softuni.bettleship_exam.sec;

import com.softuni.bettleship_exam.model.ShipEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;

@Component
@SessionScope
public class CurrentUser {

    private Long id;

    private String username;

    private String password;

    private boolean isLoggedIn;

    public Long getId() {
        return id;
    }

    public CurrentUser setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public CurrentUser setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public CurrentUser setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public CurrentUser setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }

    public void clean() {
        setLoggedIn(false)
                .setId(null)
                .setUsername(null);
    }

    @Override
    public String toString() {
        return "CurrentUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isLoggedIn=" + isLoggedIn +
                '}';
    }
}
