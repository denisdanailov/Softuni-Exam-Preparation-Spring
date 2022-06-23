package com.softuni.bettleship_exam.web;

import com.softuni.bettleship_exam.model.dtos.AttackerDTO;
import com.softuni.bettleship_exam.model.dtos.CreateShipDTO;
import com.softuni.bettleship_exam.sec.CurrentUser;
import com.softuni.bettleship_exam.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    private final ShipService shipService;
    private final CurrentUser currentUser;

    public HomeController(ShipService shipService, CurrentUser currentUser) {
        this.shipService = shipService;
        this.currentUser = currentUser;
    }

    @ModelAttribute(name = "attackerDTO")
    public AttackerDTO attackerDTO() {
        return new AttackerDTO();
    }


    @GetMapping("/")
    public String loggedOutIndex() {
        return "index";
    }

    @GetMapping("/home")
    public String loggInIndex(Model model) {

        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        model.addAttribute("getAllWithoutCurrentUser", shipService.getAllWithoutCurrentUser(currentUser.getId()));
        model.addAttribute("currentUserShips", shipService.findAllByUserId(currentUser.getId()));
        model.addAttribute("AllShips", shipService.getAll());

        return "home";
    }

    @PostMapping("/home")
    public String attacked(AttackerDTO attackerDTO) {

        shipService.reduceShipValues(attackerDTO);

        return "redirect:/home";
    }


}

