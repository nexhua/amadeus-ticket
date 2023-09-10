package com.amadeus.ticket.repositories;

import com.amadeus.ticket.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Integer> {
}
