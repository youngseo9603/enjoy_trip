package com.example.trip.repository;

import com.example.trip.domain.AttractionDescription;
import com.example.trip.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AttractionDescriptionRepository extends JpaRepository<AttractionDescription, Long> {

    AttractionDescription findById(int id);
}
