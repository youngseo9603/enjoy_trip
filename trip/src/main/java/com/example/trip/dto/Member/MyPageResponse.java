package com.example.trip.dto.Member;

import com.example.trip.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@AllArgsConstructor
public class MyPageResponse {

    private Long memberIndex;
    private String loginId;
    private String nickname;
    private String name;
    private String email;
}
