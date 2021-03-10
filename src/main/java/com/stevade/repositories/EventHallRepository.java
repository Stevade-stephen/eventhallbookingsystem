package com.stevade.repositories;

import com.stevade.models.EventHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventHallRepository extends JpaRepository<EventHall, Long> {
    List<EventHall> findAllByAvailabilityIsContainingAndAppUserIsNull(String available);

}
