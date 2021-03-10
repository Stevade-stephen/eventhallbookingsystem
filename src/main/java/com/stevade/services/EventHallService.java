package com.stevade.services;

import com.stevade.models.AppUser;
import com.stevade.models.EventHall;

import java.sql.Date;
import java.util.List;

public interface EventHallService {
    EventHall addEventHall(EventHall eventHall);
    void deleteEventHall (long id);
    List<EventHall> getAllEventHalls();
    List<EventHall> getAvailableEventHalls();
    EventHall getEventHall(long id) throws Exception;
    void bookEventHall(long id, AppUser appUser, Date date);

}
