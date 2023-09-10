package com.amadeus.ticket.inputs;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Optional;

@NoArgsConstructor
@Getter
@Setter
public class SearchInput extends PagingInput {

    private Integer departureAirport;

    private Integer arrivalAirport;

    private String departureDateTime;

    private Optional<String> returnDateTime = Optional.empty();
}
