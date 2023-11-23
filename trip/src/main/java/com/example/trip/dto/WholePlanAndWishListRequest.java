package com.example.trip.dto;

import com.example.trip.domain.Wish;
import com.example.trip.dto.Plan.CreateWholePlanRequest;
import com.example.trip.dto.Wish.WishRequest;
import com.example.trip.dto.Wish.WishResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WholePlanAndWishListRequest {

    CreateWholePlanRequest createWholePlanRequest;
    List<WishResponse> wishRequests;
}
