package com.stevade.services;

import com.stevade.models.AppUser;
import com.stevade.models.AppUserRole;
import com.stevade.models.Role;

public interface RoleService {
    Role getAppUserRole(AppUserRole userRole);
}
