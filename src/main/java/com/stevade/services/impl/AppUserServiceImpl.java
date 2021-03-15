package com.stevade.services.impl;

import com.stevade.models.AppUser;
import com.stevade.models.Role;
import com.stevade.repositories.AppUserRepository;
import com.stevade.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


@Service
public class AppUserServiceImpl implements AppUserService {


    private final AppUserRepository appUserRepository;


    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser getAppUserByEmail(String email){
        Optional<AppUser> appUser = appUserRepository.findByEmail(email);
        if(!appUser.isPresent()){
            throw new IllegalStateException("User does not exist");
        }
        return appUser.get();
    }

    @Override
    public void saveAppUser(AppUser appUser) {
        appUserRepository.save(appUser);
    }

    @Override
    public AppUser getAppUserById(long id) {
        return appUserRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteAppUserById(long id) {
        appUserRepository.deleteById(id);
    }

    @Override
    public AppUser getAppUserByEmailAndPassword(String email, String password) {
        return appUserRepository.findByEmailAndPassword(email, password).orElse(null);
    }

    @Override
    public List<AppUser> getUsersByAppUserRole(Role role) {
        return appUserRepository.findByRoles(role);
    }



}
