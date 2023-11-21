package com.example.trip.controller;

import com.example.trip.controller.constant.Message;
import com.example.trip.domain.Board;
import com.example.trip.domain.Member;
import com.example.trip.dto.Board.BoardDetailResponse;
import com.example.trip.dto.Board.BoardRegisterRequest;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;
    private final MemberService memberService;
    @GetMapping("/list")
    public ResponseEntity<?> getAllBoard(){
        List<ListBoardResponse> list = boardService.findAll();
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        Message message;
        if(list.isEmpty()){
            message = new Message(400, "보드 리스트 없음");
        }
        else{
            message = new Message(200, "보드 리스트 조회 성공", list);
        }

        return new ResponseEntity<>(message, header, HttpStatus.OK);
    }

    @GetMapping("/{boardIndex}")
    public ResponseEntity<?> getDetailBoard(@PathVariable Long boardIndex){
        BoardDetailResponse ret = boardService.findByBoardIndex(boardIndex);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));


        Message message;
        if(ret==null){
            message = new Message(400, "해당 보드는 없습니다.");
        }
        else{
            message = new Message(200, "보드 디테일 조회 성공", ret);
        }

        return new ResponseEntity<>(message, header, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerBoard(@RequestBody BoardRegisterRequest boardRegisterRequest){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Message message;

//        Member member = memberService.findMemberByIndex(memberIndex);
//        if(member == null){
//            message = new Message(400,"해당 멤버 없음");
//        }
//        else{
            boardService.saveBoard(boardRegisterRequest);
            message = new Message(200, "보드 등록 성공");
//        }

        return new ResponseEntity<>(message, header, HttpStatus.OK);
    }

    @PutMapping("/{boardIndex}")
    public ResponseEntity<?> modifyBoard(@PathVariable Long boardIndex, @RequestBody Board req){
        Message message;
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        BoardDetailResponse boardDetailResponse = boardService.findByBoardIndex(boardIndex);
        if(boardDetailResponse==null){
            message = new Message(400,"해당 게시판이 없습니다.");
        }
        else{
            boardDetailResponse.setBoardContent(req.getBoardContent());
            boardDetailResponse.setBoardTitle(req.getBoardTitle());
            boardService.modifyBoard(boardDetailResponse);


            message = new Message(200, "보드 수정 성공");
        }

        return new ResponseEntity<>(message, header, HttpStatus.OK);
    }

    @DeleteMapping("/{boardIndex}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardIndex){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Message message;

        if(boardService.findByBoardIndex(boardIndex)==null){
            message = new Message(400, "해당 게시판이 없습니다.");
        }
        else{
            boardService.deleteBoard(boardIndex);
            message = new Message(200, "보드 삭제 성공");
        }

        return new ResponseEntity<>(message, header, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<?> searchBoards(@RequestParam String query){
        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        Message message;
        List<BoardDetailResponse> ret = boardService.searchBoards(query);
        if(ret.isEmpty()){
            message = new Message(400, "게시판이 없습니다.");
        }
        else{
            message = new Message(200, "게시판 검색 성공", ret);
        }

        return new ResponseEntity<>(message, header, HttpStatus.OK);
    }
}
