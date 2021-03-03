package com.stevade.services;

import com.stevade.models.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser getAppUserByEmail(String email);
    void saveAppUser(AppUser appUser);
    AppUser getAppUserById(long id);
    void deleteAppUserById(long id);

    AppUser getAppUserByEmailAndPassword(String email, String password);
    List<AppUser> getAppUserByAppUserRole(AppUser appUser) throws Exception;

}
