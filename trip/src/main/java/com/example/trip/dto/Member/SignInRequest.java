package com.example.trip.dto.Member;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignInRequest {


    private String loginId;
    private String password;
    private String name;
    private String nickname;
    private String email;

}
