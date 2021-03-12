package com.stevade.models;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToMany;
import java.util.Set;
@Data
public class UserDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String roles;
}
