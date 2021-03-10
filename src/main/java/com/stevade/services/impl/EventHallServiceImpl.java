package com.stevade.services.impl;

import com.stevade.models.AppUser;
import com.stevade.models.AppUserRole;
import com.stevade.models.EventHall;
import com.stevade.repositories.EventHallRepository;
import com.stevade.services.EventHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventHallServiceImpl implements EventHallService {

    private final EventHallRepository eventHallRepository;

    @Autowired
    public EventHallServiceImpl(EventHallRepository eventHallRepository) {
        this.eventHallRepository = eventHallRepository;
    }

    @Override
    public EventHall addEventHall(EventHall eventHall) {
        return eventHallRepository.save(eventHall);
    }


    @Override
    public void deleteEventHall(long id) {
        eventHallRepository.deleteById(id);
    }

    @Override
    public List<EventHall> getAllEventHalls() {
        return eventHallRepository.findAll();
    }

    @Override
    public List<EventHall> getAvailableEventHalls() {
        return eventHallRepository.findAllByAvailabilityIsContainingAndAppUserIsNull("available");
    }

    @Override
    public EventHall getEventHall(long id) throws Exception {
        Optional<EventHall> optionalEventHall = eventHallRepository.findById(id);
        if(optionalEventHall.isPresent()){
            return optionalEventHall.get();
        } else{
            throw new Exception("Event Hall with " + id + " does not exist");
        }

    }

    @Override
    public void bookEventHall(long id, AppUser appUser, Date date) {
       EventHall eventHall = eventHallRepository.findById(id).get();
       eventHall.setDate(date);

       eventHall.setAppUser(appUser);
       eventHall.setAvailability("Unavailable");
       eventHallRepository.save(eventHall);
    }

}
