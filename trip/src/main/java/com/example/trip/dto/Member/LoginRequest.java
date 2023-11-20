package com.example.trip.dto.Member;

import lombok.*;

import javax.persistence.Column;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginRequest {

    private String loginId;
    private String password;
}
