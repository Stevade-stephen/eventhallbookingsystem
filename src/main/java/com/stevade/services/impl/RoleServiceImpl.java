package com.stevade.services.impl;

import com.stevade.models.AppUser;
import com.stevade.models.Role;
import com.stevade.repositories.RoleRepository;
import com.stevade.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    private  final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role getAppUserRole(AppUser appUser) {
        return roleRepository.findByAppUserRole((Enum) appUser.getRoles());
    }
}
