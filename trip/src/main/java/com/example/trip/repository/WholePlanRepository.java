package com.example.trip.repository;

import com.example.trip.domain.WholePlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WholePlanRepository extends JpaRepository<WholePlan, Long> {
    WholePlan findByWholePlanIndex(Long wholePlanIndex);
}
