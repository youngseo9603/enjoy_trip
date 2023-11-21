package com.example.trip.repository;


import com.example.trip.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.awt.print.Pageable;
import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
        List<Board> findAll();
        Board findByBoardIndex(Long boardIndex);

        List<Board> findByBoardTitleContaining(String boardTitle);



}
