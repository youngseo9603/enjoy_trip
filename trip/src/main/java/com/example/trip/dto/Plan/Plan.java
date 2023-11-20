package com.example.trip.dto.Plan;

import com.example.trip.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Plan {

    private Long placeIndex;
    private Address address;
    private String placeName;
    private String category;
    private int order;

}
