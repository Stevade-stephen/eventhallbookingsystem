package com.stevade.controllers;

import com.stevade.models.AppUser;
import com.stevade.services.AppUserService;
import com.stevade.services.impl.AppUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class RegistrationController {

    private final AppUserService appUserService;

    @Autowired
    public RegistrationController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/register")
    public String registerUserForm(Model model){
        model.addAttribute("appUser", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute ("appUser") AppUser appUser, Model model){
        appUserService.saveAppUser(appUser);
        return "login";
    }


}
