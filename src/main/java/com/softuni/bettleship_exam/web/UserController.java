package com.softuni.bettleship_exam.web;

import com.softuni.bettleship_exam.model.dtos.UserLoginDTO;
import com.softuni.bettleship_exam.model.dtos.UserRegistrationDTO;
import com.softuni.bettleship_exam.sec.CurrentUser;
import com.softuni.bettleship_exam.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;
    private final CurrentUser currentUser;

    public UserController(UserService userService, CurrentUser currentUser) {
        this.userService = userService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(value = "userRegistrationDTO")
    public UserRegistrationDTO userRegistrationDTO() {
        return new UserRegistrationDTO();
    }

    @ModelAttribute(value = "userLoginDTO")
    public UserLoginDTO userLoginDTO() {
        return new UserLoginDTO();
    }


    @GetMapping("/register")
    public String register() {

        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserRegistrationDTO userRegistrationDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes
                               ) {

        if (bindingResult.hasErrors() || !userService.registerUser(userRegistrationDTO)) {
            redirectAttributes.addFlashAttribute("userRegistrationDTO", userRegistrationDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userRegistrationDTO", bindingResult);

            return "redirect:/register";
        }
        userService.registerUser(userRegistrationDTO);

        return "redirect:/login";

    }

    @GetMapping("/login")
    public String login() {

        if (currentUser.isLoggedIn()) {
            return "redirect:/";
        }
        return "login";

    }

    @PostMapping("/login")
    public String loginUser(@Valid UserLoginDTO userLoginDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userLoginDTO", bindingResult);

            return "redirect:/login";
        }

        if (!this.userService.loginUser(userLoginDTO)) {
            redirectAttributes.addFlashAttribute("userLoginDTO", userLoginDTO);
            redirectAttributes.addFlashAttribute("badCredentials", true);

            return "redirect:/login";
        }

        userService.loginUser(userLoginDTO);

        return "redirect:/home";
    }

    @GetMapping("/logout")
    public String logout() {

        userService.logout();
        return "redirect:/";
    }
}
