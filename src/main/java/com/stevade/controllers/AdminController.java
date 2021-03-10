package com.stevade.controllers;

import com.stevade.models.AppUser;
import com.stevade.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class AdminController {

    private final AppUserService appUserService;

    @Autowired
    public AdminController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }
    @GetMapping("/admin-dashboard")
    public String adminHomePage(Model model, HttpSession session) throws Exception {
        AppUser appUser = (AppUser) session.getAttribute("adminAppUser");
        if (appUser != null){
            model.addAttribute("listOfCustomers", appUserService.getAppUserByAppUserRole(appUser));
            return "admin-home";
        }
        return "redirect:/login";

    }

    @GetMapping("/view-customer-activity/{id}")
    public String viewCustomerActivity(@PathVariable long id, Model model, HttpSession session){
        AppUser appUser = appUserService.getAppUserById(id);
        session.setAttribute("customerAppUser", appUser);
        model.addAttribute("customerAppUser", appUser);

        return "view-customer-activity";
    }




}
