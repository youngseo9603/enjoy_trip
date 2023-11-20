package com.example.trip.repository;

import com.example.trip.domain.MemberPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MemberPlanRepository extends JpaRepository<MemberPlan, Long> {
    List<MemberPlan> findAllByMember_MemberIndex(Long memberIndex);

}
