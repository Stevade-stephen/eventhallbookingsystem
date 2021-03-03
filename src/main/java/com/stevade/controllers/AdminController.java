package com.stevade.controllers;

import com.stevade.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final AppUserService appUserService;

    @Autowired
    public AdminController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }
    @GetMapping("/admin-dashboard")
    public String adminHomePage(Model model){

        return "admin-home";
    }
}
