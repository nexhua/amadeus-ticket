package com.amadeus.ticket.components;

import com.amadeus.ticket.entities.Airport;
import com.amadeus.ticket.entities.Flight;
import com.amadeus.ticket.repositories.AirportRepository;
import com.amadeus.ticket.repositories.FlightRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class DailyFlightUpdateTask {

    private final FlightRepository flightRepository;

    private final AirportRepository airportRepository;

    public DailyFlightUpdateTask(FlightRepository flightRepository, AirportRepository airportRepository) {
        this.flightRepository = flightRepository;
        this.airportRepository = airportRepository;
    }

    @Scheduled(cron = "@daily")
    public void updateFlights() {

        List<Airport> airportList = this.airportRepository.findAll();

        int airportCount = airportList.size();

        if (airportCount > 0) {
            List<Flight> flights = DailyFlightUpdateTask.createRandomFlights(airportList, 50);

            for (Flight flight : flights) {

                this.flightRepository.save(flight);
            }
        }
    }


    public static List<Flight> createRandomFlights(List<Airport> airportList, int flightCount) {

        int airportCount = airportList.size();

        List<Flight> flights = new ArrayList<>();

        while (flightCount > 0) {
            int randomDeparture = ThreadLocalRandom.current().nextInt(0, airportCount);
            int randomArrival = ThreadLocalRandom.current().nextInt(0, airportCount);

            if (randomDeparture != randomArrival) {
                Flight flight = new Flight();

                // Flights will be generated from 08:00 clock to 20:00
                // Departure hour is chosen randomly
                LocalTime startTime = LocalTime.of(8, 0, 0);
                LocalTime endTime = LocalTime.of(20, 0, 0);

                int betweenTimeSeconds = ThreadLocalRandom.current().nextInt(startTime.toSecondOfDay(), endTime.toSecondOfDay());
                int halfHour = 30 * 60;

                betweenTimeSeconds -= betweenTimeSeconds % halfHour;


                // Randomly selected departure hour
                LocalTime randomDepartureHour = LocalTime.ofSecondOfDay(betweenTimeSeconds);

                // Random departure day ranging from today or tomorrow
                int daysToAdd = ThreadLocalRandom.current().nextInt(0, 2);
                LocalDate departureDay = LocalDate.now().plusDays(daysToAdd);


                LocalDateTime departureDateTime = departureDay.atStartOfDay().with(randomDepartureHour);


                OffsetDateTime departureOffsetDateTime = OffsetDateTime.of(departureDateTime, ZoneOffset.UTC);
                OffsetDateTime arrivalOffsetDateTime = OffsetDateTime.from(departureOffsetDateTime);

                int flightLengthInHours = ThreadLocalRandom.current().nextInt(1, 4);

                arrivalOffsetDateTime = arrivalOffsetDateTime.plusHours(flightLengthInHours);


                flight.setDepartureAirport(airportList.get(randomDeparture));
                flight.setArrivalAirport(airportList.get(randomArrival));
                flight.setDepartureDateTime(departureOffsetDateTime);
                flight.setArrivalDateTime(arrivalOffsetDateTime);
                flight.setPrice(ThreadLocalRandom.current().nextInt(100, 2001));

                flights.add(flight);
            }


            flightCount--;
        }

        return flights;
    }
}
