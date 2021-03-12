package com.stevade.services.impl;

import com.stevade.models.AppUser;
import com.stevade.models.AppUserRole;
import com.stevade.repositories.AppUserRepository;
import com.stevade.services.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AppUserServiceImpl implements AppUserService {


    private final AppUserRepository appUserRepository;


    @Autowired
    public AppUserServiceImpl(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    public AppUser getAppUserByEmail(String email){
        AppUser appUser = new AppUser();
        boolean userExists = appUserRepository.findByEmail(email).isPresent();
        if(userExists){
            throw new IllegalStateException("email already taken");
        }
        appUser.setEmail(email);
        return appUser;
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
    public List<AppUser> getAppUserByAppUserRole(AppUser appUser) {
        return appUserRepository.findByRoles(AppUserRole.CUSTOMER);
    }



}
