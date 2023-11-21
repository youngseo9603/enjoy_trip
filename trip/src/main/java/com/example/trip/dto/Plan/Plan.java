package com.example.trip.dto.Plan;

import com.example.trip.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Plan {

    private Long planIndex;
    private Address address;
    private String placeName;
    private String category;
    private int orders;

}
