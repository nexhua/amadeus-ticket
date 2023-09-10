package com.amadeus.ticket.controllers;

import com.amadeus.ticket.inputs.SearchInput;
import com.amadeus.ticket.repositories.FlightRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping("/search")
public class SearchController {

    private final FlightRepository flightRepository;

    public SearchController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping
    public ResponseEntity<?> searchFlights(@Valid SearchInput input) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (input.getReturnDateTime().isEmpty()) {
            try {
                LocalDate departureDate = LocalDate.parse(input.getDepartureDateTime(), formatter);

                return ResponseEntity.ok().body(this.flightRepository.findFlightsByDepartureWithPagination(
                        input.getDepartureAirport(),
                        input.getArrivalAirport(),
                        departureDate,
                        PageRequest.of(input.getPageNumber(), input.getPageSize())
                ));

            } catch (DateTimeParseException ex) {
                return ResponseEntity.badRequest().build();
            }
        } else {
            try {
                LocalDate departureDate = LocalDate.parse(input.getDepartureDateTime(), formatter);

                LocalDate returnDate = LocalDate.parse(input.getReturnDateTime().get(), formatter);

                return ResponseEntity.ok().body(this.flightRepository.findBothWayFlightsWithPagination(
                        input.getDepartureAirport(),
                        input.getArrivalAirport(),
                        departureDate,
                        returnDate,
                        PageRequest.of(input.getPageNumber(), input.getPageSize())
                ));

            } catch (DateTimeParseException ex) {
                return ResponseEntity.badRequest().build();
            }
        }
    }
}
