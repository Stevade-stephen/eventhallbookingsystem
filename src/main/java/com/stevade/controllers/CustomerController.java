package com.stevade.controllers;

import com.stevade.models.EventHall;
import com.stevade.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

    private final AppUserService appUserService;

    @Autowired
    public CustomerController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/customer-dashboard")
    public String customerHomePage(Model model){
        return "customer-home";
    }


    @GetMapping("/view-event-hall")
    public String viewHalls (Model model) {
        EventHall eventHall = new EventHall();
        model.addAttribute("hall", eventHall);
        return "event-hall";
    }
}
