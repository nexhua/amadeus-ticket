package com.amadeus.ticket.controllers;

import com.amadeus.ticket.entities.Flight;
import com.amadeus.ticket.repositories.FlightRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flights")
public class FlightController {


    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Flight>> getFlights(Pageable pageable) {
        return ResponseEntity.ok().body(this.flightRepository.findAll(pageable));
    }
}
