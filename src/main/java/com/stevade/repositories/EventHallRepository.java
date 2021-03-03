package com.stevade.repositories;

import com.stevade.models.EventHall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventHallRepository extends JpaRepository<EventHall, Long> {

}
