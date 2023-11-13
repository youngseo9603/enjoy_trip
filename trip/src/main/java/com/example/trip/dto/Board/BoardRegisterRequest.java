package com.example.trip.dto.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardRegisterRequest {
    Long memberIndex;
    String boardTitle;
    String boardContent;
    LocalDateTime createTime;
}
