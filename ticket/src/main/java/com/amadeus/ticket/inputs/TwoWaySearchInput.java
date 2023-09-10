package com.amadeus.ticket.inputs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TwoWaySearchInput extends PagingInput {

    private Integer departureAirport;

    private Integer arrivalAirport;
}
