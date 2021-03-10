package com.stevade.controllers;

import com.stevade.models.AppUser;
import com.stevade.services.AppUserService;
import com.stevade.services.EventHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
public class CustomerController {

    private final AppUserService appUserService;
    private final EventHallService eventHallService;

    @Autowired
    public CustomerController(AppUserService appUserService, EventHallService eventHallService) {
        this.appUserService = appUserService;
        this.eventHallService = eventHallService;
    }

    @GetMapping("/customer-dashboard")
    public String customerHomePage(Model model, HttpSession session){
        AppUser appUser = (AppUser) session.getAttribute("customerAppUser");
        if(appUser == null){
            return "redirect:/login";
        }
        model.addAttribute("hallList", eventHallService.getAvailableEventHalls());

        return "event-hall";

    }


//    @GetMapping("/view-event-hall")
//    public String viewHalls (Model model) {
//        EventHall eventHall = new EventHall();
//        model.addAttribute("hall", eventHall);
//        return "event-hall";
//    }

}
