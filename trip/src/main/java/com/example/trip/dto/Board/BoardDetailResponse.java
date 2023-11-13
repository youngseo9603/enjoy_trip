package com.example.trip.dto.Board;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BoardDetailResponse {
    Long boardIndex;
    Long memberIndex;
    String boardTitle;
    String boardContent;
    LocalDate createTime;
}
