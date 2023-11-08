package com.example.trip.dto.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ListBoardResponse {
    private Long boardIndex;
    private Long memberIndex;
    private String boardTitle;
}
