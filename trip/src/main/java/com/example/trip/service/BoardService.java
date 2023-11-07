package com.example.trip.service;

import com.example.trip.domain.Board;
import com.example.trip.dto.Board.UpdateBoardRequest;
import com.example.trip.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private BoardRepository boardRepository;

    public Board findById(Long boardIndex){return boardRepository.findById(boardIndex).orElseThrow();}

    public void makeBoard(Board board) {
        boardRepository .save(board);
    }

    public void updateBoard(Board board, UpdateBoardRequest request){
        board.setBoardId(request.getBoardId());
        board.setBoardTitle(request.getBoardTitle());
        board.setBoardContent(request.getBoardContent());
        boardRepository.save(board);
    }

    public void deleteBoard(Board board){
        boardRepository.delete(board);
    }


}
