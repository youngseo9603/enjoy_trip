package com.example.trip.service;

import com.example.trip.domain.Board;
import com.example.trip.domain.Member;
import com.example.trip.dto.Board.BoardDetailResponse;
import com.example.trip.dto.Board.BoardRegisterRequest;
import com.example.trip.dto.Board.ListBoardResponse;
import com.example.trip.dto.Board.UpdateBoardRequest;
import com.example.trip.repository.BoardRepository;
import com.example.trip.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public List<ListBoardResponse> findAll(){
        List<Board> list = boardRepository.findAll();
        List<ListBoardResponse> responseList = new ArrayList<>();
        for(Board b : list){
            responseList.add(new ListBoardResponse(b.getBoardIndex(), b.getBoardTitle(), b.getCreateTime()));
        }
        return responseList;
    }

    public BoardDetailResponse findByBoardIndex(Long boardIndex){
        Board board = boardRepository.findByBoardIndex(boardIndex);
        if(board==null) return null;

        BoardDetailResponse ret = new BoardDetailResponse(board.getBoardIndex(), board.getMember().getMemberIndex(), board.getBoardTitle(), board.getBoardContent(), board.getCreateTime());
        return ret;
    }

    public void saveBoard(BoardRegisterRequest boardRegisterRequest){
        Board board = new Board();
        board.setBoardIndex(null);
        board.setMember(memberRepository.findByMemberIndex(boardRegisterRequest.getMemberIndex()));
        board.setBoardTitle(boardRegisterRequest.getBoardTitle());
        board.setBoardContent(boardRegisterRequest.getBoardContent());
        board.setCreateTime(LocalDateTime.now());

        boardRepository.save(board);
    }

    public void modifyBoard(BoardDetailResponse boardDetailResponse){
        Board board = new Board();
        board.setBoardIndex(boardDetailResponse.getBoardIndex());
        board.setMember(memberRepository.findByMemberIndex(boardDetailResponse.getMemberIndex()));
        board.setBoardTitle(boardDetailResponse.getBoardTitle());
        board.setBoardContent(boardDetailResponse.getBoardContent());
        board.setCreateTime(LocalDateTime.now());

        boardRepository.save(board);
    }

    public List<BoardDetailResponse> searchBoards(String boardTitle){
        List<BoardDetailResponse> ret = new ArrayList<>();
        List<Board> boards = boardRepository.findByBoardTitleContaining(boardTitle);
        for(Board b: boards){
            ret.add(new BoardDetailResponse(b.getBoardIndex(), b.getMember().getMemberIndex(),b.getBoardTitle(),b.getBoardContent(),b.getCreateTime()));
        }

        return ret;
    }

    public void deleteBoard(Long boardIndex) {
        boardRepository.deleteById(boardIndex);
    }

    public List<ListBoardResponse> findBoardByMemberIndex(Long memberIndex){
        Member member = memberRepository.findByMemberIndex(memberIndex);
        List<Board> boards = member.getBoards();

        List<ListBoardResponse> ret = new ArrayList<>();
        for(Board b: boards){
            ListBoardResponse tmp = ListBoardResponse.builder()
                    .boardIndex(b.getBoardIndex())
                    .boardTitle(b.getBoardTitle())
                    .createTime(b.getCreateTime())
                    .build();
            ret.add(tmp);
        }


        return ret;
    }
}
