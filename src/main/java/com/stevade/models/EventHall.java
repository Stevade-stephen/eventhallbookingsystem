package com.stevade.models;


import lombok.Data;
import lombok.EqualsAndHashCode;


import javax.persistence.*;
import java.sql.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data

public class EventHall  extends BaseModel{

    private String name;
    private String availability;
    private String location;
    @Lob
    private String hallUrl;
    private String price;
    private Date date;

    @OneToOne
    private AppUser appUser;


}
