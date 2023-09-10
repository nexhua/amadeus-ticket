package com.amadeus.ticket.controllers;

import com.amadeus.ticket.entities.Flight;
import com.amadeus.ticket.inputs.PagingInput;
import com.amadeus.ticket.repositories.FlightRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/flights")
public class FlightController {


    private final FlightRepository flightRepository;

    public FlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping
    public ResponseEntity<Page<Flight>> getFlights(@Valid PagingInput pagingInput) {
        return ResponseEntity.ok().body(this.flightRepository.findAll(PageRequest.of(pagingInput.getPageNumber(), pagingInput.getPageSize())));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> findFlight(@RequestParam Integer id) {
        Optional<Flight> optional = this.flightRepository.findById(id);

        return optional.map(flight -> ResponseEntity.status(HttpStatus.FOUND).body(flight)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<?> createFlight(Flight flight) {
        this.flightRepository.save(flight);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<Flight> updateFlight(Flight flight) {
        return ResponseEntity.ok().body(this.flightRepository.save(flight));
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFlight(Flight flight) {
        this.flightRepository.delete(flight);
        return ResponseEntity.ok().build();
    }
}
