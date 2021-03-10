package com.stevade.models;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    @Enumerated(EnumType.STRING)
    private AppUserRole appUserRole;


    public AppUser(String firstName, String lastName, String email,
                   String password, AppUserRole appUserRole) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.appUserRole = appUserRole;

    }


}
