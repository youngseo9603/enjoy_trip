package com.example.trip.dto.Wish;

import com.example.trip.domain.Address;
import com.example.trip.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Builder
public class WishResponse {
    private Long wishIndex;
    private Address address;
    private String placeName;
    private String category;
}
