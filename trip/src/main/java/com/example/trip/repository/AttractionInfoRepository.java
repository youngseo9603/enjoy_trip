package com.example.trip.repository;

import com.example.trip.domain.AttractionInfo;
import com.example.trip.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionInfoRepository extends JpaRepository<AttractionInfo, Long> {
    AttractionInfo findById(int content_id);

}
