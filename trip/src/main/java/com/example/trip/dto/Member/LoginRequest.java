package com.example.trip.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Column;

@Getter
@AllArgsConstructor
public class LoginRequest {

    private String loginId;
    private String password;

}
