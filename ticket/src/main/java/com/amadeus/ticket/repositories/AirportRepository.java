package com.amadeus.ticket.repositories;

import com.amadeus.ticket.entities.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
}
