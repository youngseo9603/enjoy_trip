package com.example.trip.repository;

import com.example.trip.domain.Plan;
import com.example.trip.domain.PlanDay;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanRepository extends JpaRepository<Plan, Long> {
    List<Plan> findAllByPlanDay(PlanDay planDay);
}
