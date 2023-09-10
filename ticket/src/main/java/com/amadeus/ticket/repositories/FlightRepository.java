package com.amadeus.ticket.repositories;

import com.amadeus.ticket.entities.Flight;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface FlightRepository extends PagingAndSortingRepository<Flight, Integer> {
}
