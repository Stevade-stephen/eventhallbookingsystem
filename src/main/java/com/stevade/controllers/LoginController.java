package com.stevade.controllers;

import com.stevade.models.AppUser;
import com.stevade.models.AppUserRole;
import com.stevade.models.Role;
import com.stevade.services.AppUserService;
import com.stevade.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private final AppUserService appUserService;
    private AuthenticationManager authenticationManager;
    private final RoleService roleService;
    private final UserDetailsService userDetailsService;

    @Autowired
    public LoginController(AppUserService appUserService, AuthenticationManager authenticationManager, RoleService roleService, @Qualifier("myUserDetailsService") UserDetailsService userDetailsService) {
        this.appUserService = appUserService;
        this.authenticationManager = authenticationManager;
        this.roleService = roleService;
        this.userDetailsService = userDetailsService;
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
        UserDetails userDetails = null;
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword()));
             userDetails = userDetailsService.loadUserByUsername(appUser.getEmail());
        } catch (BadCredentialsException e){
            e.printStackTrace();
        }


        AppUser newUser = appUserService.getAppUserByEmailAndPassword(appUser.getEmail(), appUser.getPassword());


        if (newUser == null) {
            redirectAttributes.addFlashAttribute("invalid", "User does not exist. Check login details or register");
            return "redirect:/login";
        } else {

            Role role = new Role();

//            if (newUser.getRoles().equals(role.getAppUserRole().toString().equals(AppUserRole.ADMIN))) {
            assert userDetails != null;
            if(userDetails.getAuthorities().equals(AppUserRole.ADMIN)){
                model.addAttribute("adminAppUser", appUser);
                session.setAttribute("adminAppUser", newUser);
                return "redirect:/admin-dashboard";
            } else {
                model.addAttribute("appUser", new AppUser());
                session.setAttribute("customerAppUser", newUser);
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
