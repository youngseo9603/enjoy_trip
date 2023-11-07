package com.example.trip.repository;

import com.example.trip.domain.AttractionDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttractionDetailRepository extends JpaRepository<AttractionDetail, Long> {
    List<AttractionDetail> findByCat1(String cat1);

}
