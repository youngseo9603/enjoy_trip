package com.example.trip.controller;

import com.example.trip.domain.Board;
import com.example.trip.domain.Member;
import com.example.trip.dto.Board.UpdateBoardRequest;
import com.example.trip.service.BoardService;
import com.example.trip.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService boardService;

    @GetMapping("/test")
    public ResponseEntity getTest(){
        return ResponseEntity.ok("success");
    }

    //보드 리스트 조회

    //선택 보드 조회
    @GetMapping()
    public ResponseEntity<?> getBoard(@RequestHeader("boardIndex") Long boardIndex){
        Board board = boardService.findById(boardIndex);

        return ResponseEntity.ok("보드 조회");
    }

    // 보드 생성 요청
    @PostMapping
    public ResponseEntity<?> makeBoard(@RequestBody Board board) {
        boardService.makeBoard(board);
        return ResponseEntity.ok("보드 생성");
    }

    // 보드 수정 요청
    @PutMapping
    public ResponseEntity<?> modifyBoard(@RequestHeader("boardIndex") Long boardIndex, @RequestBody UpdateBoardRequest request){
        Board board = boardService.findById(boardIndex);
        boardService.updateBoard(board, request);
        return ResponseEntity.ok("보드 수정");
    }

    // 보드 삭제 요청
    @DeleteMapping
    public ResponseEntity<?> deleteBoard(@RequestHeader("boardIndex") Long boardIndex){
        Board board = boardService.findById(boardIndex);
        boardService.deleteBoard(board);
        return ResponseEntity.ok("보드 삭제");
    }

}
