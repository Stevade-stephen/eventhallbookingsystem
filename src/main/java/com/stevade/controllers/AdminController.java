package com.stevade.controllers;

import com.stevade.models.AppUser;
import com.stevade.models.AppUserRole;
import com.stevade.models.Role;
import com.stevade.services.AppUserService;
import com.stevade.services.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@Slf4j
public class AdminController {

    private final AppUserService appUserService;
    private final RoleService roleService;

    @Autowired
    public AdminController(AppUserService appUserService, RoleService roleService, RoleService roleService1) {
        this.appUserService = appUserService;
        this.roleService = roleService1;
    }
    @GetMapping("/admin-dashboard")
    public String adminHomePage(Model model, HttpSession session) throws Exception {
        log.info("Accessing admin page");
        AppUser appUser = (AppUser) session.getAttribute("adminAppUser");
        if (appUser != null){
            Role role = roleService.getAppUserRole(AppUserRole.CUSTOMER);
            model.addAttribute("listOfCustomers", appUserService.getUsersByAppUserRole(role));
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
