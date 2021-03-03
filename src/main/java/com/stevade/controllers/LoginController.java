package com.stevade.controllers;

import com.stevade.models.AppUser;
import com.stevade.models.AppUserRole;
import com.stevade.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final AppUserService appUserService;

    @Autowired
    public LoginController(AppUserService appUserService) {
        this.appUserService = appUserService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("admin", new AppUser());
        model.addAttribute("appUser", new AppUser());
        model.addAttribute("invalid", null);
        return "login";
    }





    @PostMapping("/login")
    public String processLogin(Model model, HttpSession session, AppUser appUser, RedirectAttributes redirectAttributes) {
        AppUser newUser = appUserService.getAppUserByEmailAndPassword(appUser.getEmail(), appUser.getPassword());
        if (newUser == null) {
            redirectAttributes.addFlashAttribute("invalid", "User does not exist. Check login details or register");
            return "redirect:/login";
        } else {
            if (newUser.getAppUserRole().equals(AppUserRole.ADMIN)) {
                model.addAttribute("adminAppUser", appUser);
                session.setAttribute("appUser", newUser);
                return "redirect:/admin-dashboard";
            } else {
                model.addAttribute("appUser", new AppUser());
                session.setAttribute("appUser", newUser);
                return "redirect:/customer-dashboard";

            }
        }


    }
    @GetMapping("/logout")
    public String logout (Model model, HttpSession session)  {
        if (session != null){
            session.invalidate();
        }
        model.addAttribute("appUser", new AppUser());
        model.addAttribute("invalid", null);
        return "redirect:/login";
    }

}
