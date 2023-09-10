package com.amadeus.ticket.inputs;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PagingInput {

    @Min(0)
    int pageNumber;

    @Min(10)
    int pageSize;
}
