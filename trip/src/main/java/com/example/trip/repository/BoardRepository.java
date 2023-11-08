package com.example.trip.repository;


import com.example.trip.domain.Board;
import com.example.trip.dto.Board.ListBoardResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
        List<Board> findAll();
}
