package com.stevade.repositories;

import com.stevade.models.AppUserRole;
import com.stevade.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAppUserRole(AppUserRole userRole);
}
