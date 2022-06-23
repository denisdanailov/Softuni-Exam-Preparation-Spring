package com.softuni.bettleship_exam.web;

import com.softuni.bettleship_exam.model.ShipTypeEnum;
import com.softuni.bettleship_exam.model.dtos.CreateShipDTO;
import com.softuni.bettleship_exam.sec.CurrentUser;
import com.softuni.bettleship_exam.service.ShipService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class ShipController {

    private final ShipService shipService;
    private final CurrentUser currentUser;

    public ShipController(ShipService shipService, CurrentUser currentUser) {
        this.shipService = shipService;
        this.currentUser = currentUser;
    }


    @GetMapping("/ship-add")
    public String ships(Model model) {

        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        model.addAttribute("categories", ShipTypeEnum.values());

        return "ship-add";
    }

    @PostMapping("/ship-add")
    public String shipAdd(@Valid CreateShipDTO createShipDTO,
                          BindingResult bindingResult,
                          RedirectAttributes redirectAttributes) {

        if (!currentUser.isLoggedIn()) {
            return "redirect:/";
        }

        if (bindingResult.hasErrors() || this.shipService.create(createShipDTO)) {
            redirectAttributes.addFlashAttribute("createShipDTO", createShipDTO);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.createShipDTO", bindingResult);

            return "redirect:/ship-add";
        }

        System.out.println(createShipDTO);

        return "redirect:/home";
    }

    @ModelAttribute(value = "createShipDTO")
    public CreateShipDTO createShipDTO() {
        return new CreateShipDTO();
    }

}
