package com.stevade.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@Entity
public class AppUser extends BaseModel {

    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    //Because it is an enum, we have to specify the type which is a string
//    @Enumerated(EnumType.STRING)
//    private AppUserRole appUserRole;
    @ManyToMany
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;


    public AppUser(String firstName, String lastName, String email,
                   String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

    }


}
