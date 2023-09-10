package com.amadeus.ticket.components;

import com.amadeus.ticket.constants.AirportNames;
import com.amadeus.ticket.entities.Airport;
import com.amadeus.ticket.repositories.AirportRepository;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class OnStartDatabaseSeeder {

    private final AirportRepository airportRepository;

    public OnStartDatabaseSeeder(AirportRepository airportRepository) {
        this.airportRepository = airportRepository;
    }


    @EventListener
    public void onApplicationStart(ContextRefreshedEvent event) {

        if(this.airportRepository.count() == 0) {
            for(String name : AirportNames.airportNames) {
                Airport airport = new Airport();
                airport.setCity(name);
                this.airportRepository.save(airport);
            }
        }
    }
}
