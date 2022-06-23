package com.softuni.bettleship_exam.service.impl;

import com.softuni.bettleship_exam.model.UserEntity;
import com.softuni.bettleship_exam.model.dtos.UserLoginDTO;
import com.softuni.bettleship_exam.model.dtos.UserRegistrationDTO;
import com.softuni.bettleship_exam.repository.UserRepository;
import com.softuni.bettleship_exam.sec.CurrentUser;
import com.softuni.bettleship_exam.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    @Override
    public boolean registerUser(UserRegistrationDTO userRegistrationDTO) {

        if (!userRegistrationDTO.getPassword().equals(userRegistrationDTO.getConfirmPassword())) {
            return false;
        }

        Optional<UserEntity> byEmail = userRepository.findByEmail(userRegistrationDTO.getEmail());
        if (byEmail.isPresent()) {
            return false;
        }
        Optional<UserEntity> byUsername = userRepository.findByUsername(userRegistrationDTO.getUsername());
        if (byUsername.isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity();

        user.setUsername(userRegistrationDTO.getUsername());
        user.setFullName(userRegistrationDTO.getFullName());
        user.setEmail(userRegistrationDTO.getEmail());
        user.setPassword(userRegistrationDTO.getPassword());

        userRepository.save(user);

        return true;
    }

    @Override
    public boolean loginUser(UserLoginDTO userLoginDTO) {

        Optional<UserEntity> userEntityOpt = this.userRepository.findByUsernameAndPassword(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        if (userEntityOpt.isEmpty()) {
            return false;
        }



        currentUser.setId(userEntityOpt.get().getId());
        currentUser.setUsername(userLoginDTO.getUsername());
        currentUser.setLoggedIn(true);

        return true;
    }

    @Override
    public void logout() {
        currentUser.clean();
    }
}
