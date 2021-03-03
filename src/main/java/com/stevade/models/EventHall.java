package com.stevade.models;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class EventHall {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private boolean isAvailable = true;
    private String location;
    @Column(columnDefinition="TEXT")
    private String hallUrl;


}
