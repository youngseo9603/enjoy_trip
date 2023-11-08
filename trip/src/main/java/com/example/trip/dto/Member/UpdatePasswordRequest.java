package com.example.trip.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdatePasswordRequest {
    private Long memberIndex;
    private String password;
}
