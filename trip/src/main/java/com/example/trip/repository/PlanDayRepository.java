package com.example.trip.repository;

import com.example.trip.domain.PlanDay;
import com.example.trip.domain.WholePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlanDayRepository extends JpaRepository<PlanDay, Long> {

    List<PlanDay> findAllByWholePlan(WholePlan wholePlan);

    void deleteAllByWholePlan(WholePlan wholePlan);


}
