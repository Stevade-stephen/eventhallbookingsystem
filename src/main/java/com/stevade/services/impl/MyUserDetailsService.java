package com.stevade.services.impl;

import com.stevade.models.MyUserDetails;
import com.stevade.repositories.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;
    @Autowired
    public MyUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return MyUserDetails.build(appUserRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("")));
    }
}
