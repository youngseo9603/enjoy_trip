package com.example.trip.service;

import com.example.trip.domain.Board;
import com.example.trip.dto.Board.ListBoardResponse;
import com.example.trip.dto.Board.UpdateBoardRequest;
import com.example.trip.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;

    public List<ListBoardResponse> findAll(){
        List<Board> list = boardRepository.findAll();
        List<ListBoardResponse> responseList = new ArrayList<>();
        for(Board b : list){
            responseList.add(new ListBoardResponse(b.getBoardIndex(), b.getMember().getMemberIndex(),b.getBoardTitle()));
        }
        return responseList;
    }
//
//    public Board findById(Long boardIndex){return boardRepository.findById(boardIndex).orElseThrow();}
//
//    public void makeBoard(Board board) {
//        boardRepository .save(board);
//    }
//
//    public void updateBoard(Board board, UpdateBoardRequest request){
//        board.setBoardTitle(request.getBoardTitle());
//        board.setBoardContent(request.getBoardContent());
//        boardRepository.save(board);
//    }
//
//    public void deleteBoard(Board board){
//        boardRepository.delete(board);
//    }


}
