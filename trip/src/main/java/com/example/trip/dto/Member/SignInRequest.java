package com.example.trip.dto.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignInRequest {

    private String loginId;
    private String password;
    private String nickname;
    private String name;

}
