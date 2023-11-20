package com.example.trip.repository;

import com.example.trip.domain.MemberPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPlanRepository extends JpaRepository<MemberPlan, Long> {
}
