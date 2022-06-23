package com.softuni.bettleship_exam.service;

import com.softuni.bettleship_exam.model.dtos.UserLoginDTO;
import com.softuni.bettleship_exam.model.dtos.UserRegistrationDTO;

public interface UserService {

    boolean registerUser(UserRegistrationDTO userRegistrationDTO);

    boolean loginUser(UserLoginDTO userLoginDTO);

    void logout();
}
