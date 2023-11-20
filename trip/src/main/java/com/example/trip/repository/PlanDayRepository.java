package com.example.trip.repository;

import com.example.trip.domain.PlanDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanDayRepository extends JpaRepository<PlanDay, Long> {
}
