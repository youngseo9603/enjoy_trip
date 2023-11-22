package com.example.trip.dto.Wish;

import com.example.trip.domain.Address;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class WishRequest {
    private Address address;
    private String placeName;
    private String category;
}
