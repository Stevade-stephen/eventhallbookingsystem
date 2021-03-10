package com.stevade.repositories;

import com.stevade.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);
    Optional<AppUser> findByEmailAndPassword(String email, String password);
    List<AppUser> findAppUserByAppUserRole(Enum CUSTOMER);
}
