package com.example.trip.dto.Board;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ListBoardResponse {
    private Long boardIndex;
    private String boardTitle;
    private LocalDateTime createTime;
}
