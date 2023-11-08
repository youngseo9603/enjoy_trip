package com.example.trip.controller;

import com.example.trip.controller.constant.Message;
import com.example.trip.domain.Board;
import com.example.trip.domain.Member;
import com.example.trip.dto.Board.ListBoardResponse;
import com.example.trip.dto.Board.UpdateBoardRequest;
import com.example.trip.service.BoardService;
import com.example.trip.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllBoard(){
        List<ListBoardResponse> list = boardService.findAll();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Message message = new Message(200, "보드 리스트 조회 성공", list);

        return new ResponseEntity<>(message, header, HttpStatus.OK);
    }
//
//    @GetMapping("/test")
//    public ResponseEntity getTest(){
//        return ResponseEntity.ok("success");
//    }
//
//    //보드 리스트 조회
//
//    //선택 보드 조회
//    @GetMapping()
//    public ResponseEntity<?> getBoard(@RequestHeader("boardIndex") Long boardIndex){
//        Board board = boardService.findById(boardIndex);
//
//        return ResponseEntity.ok("보드 조회");
//    }
//
//    // 보드 생성 요청
//    @PostMapping
//    public ResponseEntity<?> makeBoard(@RequestBody Board board) {
//        boardService.makeBoard(board);
//        return ResponseEntity.ok("보드 생성");
//    }
//
//    // 보드 수정 요청
//    @PutMapping
//    public ResponseEntity<?> modifyBoard(@RequestHeader("boardIndex") Long boardIndex, @RequestBody UpdateBoardRequest request){
//        Board board = boardService.findById(boardIndex);
//        boardService.updateBoard(board, request);
//        return ResponseEntity.ok("보드 수정");
//    }
//
//    // 보드 삭제 요청
//    @DeleteMapping
//    public ResponseEntity<?> deleteBoard(@RequestHeader("boardIndex") Long boardIndex){
//        Board board = boardService.findById(boardIndex);
//        boardService.deleteBoard(board);
//        return ResponseEntity.ok("보드 삭제");
//    }

}
