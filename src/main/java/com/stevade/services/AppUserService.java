package com.stevade.services;

import com.stevade.models.AppUser;
import com.stevade.models.Role;

import java.util.List;

public interface AppUserService {
    void saveAppUser(AppUser appUser);
    AppUser getAppUserById(long id);
    AppUser getAppUserByEmail(String email);
    void deleteAppUserById(long id);
    AppUser getAppUserByEmailAndPassword(String email, String password);
    List<AppUser> getUsersByAppUserRole(Role role) throws Exception;


}
