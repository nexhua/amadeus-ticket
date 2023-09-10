package com.amadeus.ticket.controllers;

import com.amadeus.ticket.entities.Airport;
import com.amadeus.ticket.inputs.PagingInput;
import com.amadeus.ticket.repositories.AirportRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/airports")
public class AirportController {

    private final AirportRepository airportRepository;

    public AirportController(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Airport>> getAirports(@Valid PagingInput pagingInput) {
        return ResponseEntity.ok(this.airportRepository.findAll(PageRequest.of(pagingInput.getPageNumber(), pagingInput.getPageSize())));
    }

    @PostMapping
    public ResponseEntity<?> createAirport(Airport airport) {
        this.airportRepository.save(airport);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Airport> updateAirport(Airport airport) {
        return ResponseEntity.ok().body(this.airportRepository.save(airport));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAirport(Airport airport) {
        this.airportRepository.delete(airport);
        return ResponseEntity.ok().build();
    }
}
