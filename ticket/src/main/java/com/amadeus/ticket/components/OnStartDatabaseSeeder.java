package com.amadeus.ticket.components;

import com.amadeus.ticket.constants.AirportNames;
import com.amadeus.ticket.entities.Airport;
import com.amadeus.ticket.entities.Flight;
import com.amadeus.ticket.repositories.AirportRepository;
import com.amadeus.ticket.repositories.FlightRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OnStartDatabaseSeeder {

    private final AirportRepository airportRepository;

    private final FlightRepository flightRepository;

    public OnStartDatabaseSeeder(AirportRepository airportRepository, FlightRepository flightRepository) {
        this.airportRepository = airportRepository;
        this.flightRepository = flightRepository;
    }


    @EventListener
    public void onApplicationStart(ContextRefreshedEvent event) {

        if (this.airportRepository.count() == 0) {
            for (String name : AirportNames.airportNames) {
                Airport airport = new Airport();
                airport.setCity(name);
                this.airportRepository.save(airport);
            }
        }

        List<Airport> airports = this.airportRepository.findAll();

        if (airports.size() > 0 && this.flightRepository.count() <= 0) {
            List<Flight> flights = DailyFlightUpdateTask.createRandomFlights(airports, 200);

            for (Flight flight : flights) {
                this.flightRepository.save(flight);
            }
        }
    }
}
