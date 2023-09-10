package com.amadeus.ticket.repositories;

import com.amadeus.ticket.entities.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    @Query(value = "select * from flight f where f.departure_airport = :departure_airport and f.arrival_airport = :arrival_airport and date(f.departure_date_time) = :date", nativeQuery = true, countQuery = "select count(*) from flight")
    Page<Flight> findFlightsByDepartureWithPagination(Integer departure_airport, Integer arrival_airport, LocalDate date, Pageable pageable);


    @Query(value = "select * from flight f where f.departure_airport = :departure_airport and f.arrival_airport = :arrival_airport and date(f.departure_date_time) = :departure_date UNION select * from flight f where f.departure_airport = :arrival_airport and f.arrival_airport = :departure_airport and date(f.departure_date_time) = :return_date", nativeQuery = true, countQuery = "select count(*) from flight")
    Page<Flight> findBothWayFlightsWithPagination(Integer departure_airport, Integer arrival_airport, LocalDate departure_date, LocalDate return_date, Pageable pageable);
}
