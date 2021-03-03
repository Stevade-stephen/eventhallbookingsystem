package com.stevade.services.impl;

import com.stevade.models.EventHall;
import com.stevade.repositories.EventHallRepository;
import com.stevade.services.EventHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
