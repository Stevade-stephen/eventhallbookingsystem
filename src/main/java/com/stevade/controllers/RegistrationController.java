package com.stevade.controllers;

import com.stevade.models.AppUser;
import com.stevade.models.AppUserRole;
import com.stevade.models.Role;
import com.stevade.models.UserDTO;
import com.stevade.repositories.RoleRepository;
import com.stevade.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegistrationController {

    private final AppUserService appUserService;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(AppUserService appUserService, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.appUserService = appUserService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String registerUserForm(Model model){
        model.addAttribute("appUser", new UserDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute ("appUser") UserDTO userDTO, Model model){
        AppUser appUser = new AppUser();
        Set<Role> roles = new HashSet<>();
        if (userDTO.getRoles().equalsIgnoreCase("admin") || userDTO.getRoles().equalsIgnoreCase("customer"))
            appUser.setFirstName(userDTO.getFirstName());
            appUser.setLastName(userDTO.getLastName());
            appUser.setEmail(userDTO.getEmail());
            appUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            roles.add(roleRepository.findByAppUserRole(AppUserRole.ADMIN));
            appUser.setRoles(roles);
            appUserService.saveAppUser(appUser);
            return "login";

    }


}
