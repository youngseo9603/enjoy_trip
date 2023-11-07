package com.example.trip.dto.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateBoardRequest {

    private String boardId;
    private String boardTitle;
    private Long boardContent;

}
