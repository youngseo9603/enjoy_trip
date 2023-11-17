package com.example.trip.dto.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ListBoardResponse {
    private Long boardIndex;
    private String boardTitle;
    private LocalDateTime createTime;
}
