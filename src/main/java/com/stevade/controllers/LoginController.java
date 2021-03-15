package com.stevade.controllers;

import com.stevade.models.AppUser;
import com.stevade.models.AppUserRole;
import com.stevade.models.Role;
import com.stevade.services.AppUserService;
import com.stevade.services.RoleService;
import com.stevade.services.impl.MyUserDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;

@Controller
@Slf4j
public class LoginController {

    private final AppUserService appUserService;
    private AuthenticationManager authenticationManager;
    private final RoleService roleService;
    private final MyUserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    public LoginController(AppUserService appUserService, AuthenticationManager authenticationManager, RoleService roleService, MyUserDetailsService userDetailsService) {
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
//        UserDetails userDetails = null;
        log.info("Authenticating user");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(appUser.getEmail(), appUser.getPassword()));
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(appUser.getEmail(), null, Collections.emptyList());
            SecurityContextHolder.getContext().setAuthentication(auth);
        } catch (BadCredentialsException e){
            e.printStackTrace();
        }

        log.info("Successfully authenticated");
        log.info(appUser.getPassword());


        log.info("Getting user from database");
        AppUser newUser = appUserService.getAppUserByEmail(appUser.getEmail());
        log.info("Getting user from database successful");


        if (newUser == null) {
            redirectAttributes.addFlashAttribute("invalid", "User does not exist. Check login details or register");
            return "redirect:/login";
        } else {

//            Role role = new Role();
            log.info("Authorising user Role('ADMIN')");

            if (newUser.getRoles().stream().anyMatch(role -> role.getAppUserRole().equals(AppUserRole.ADMIN))) {
                log.info("User is Admin");
                model.addAttribute("adminAppUser", appUser);
                session.setAttribute("adminAppUser", newUser);
                return "redirect:/admin-dashboard";
            } else {
                log.info("User is Customer");
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
