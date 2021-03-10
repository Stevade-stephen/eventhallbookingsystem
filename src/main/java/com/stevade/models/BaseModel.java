package com.stevade.models;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
}
