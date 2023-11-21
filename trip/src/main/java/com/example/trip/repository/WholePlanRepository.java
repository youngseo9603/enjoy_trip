package com.example.trip.repository;

import com.example.trip.domain.Member;
import com.example.trip.domain.WholePlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WholePlanRepository extends JpaRepository<WholePlan, Long> {
    WholePlan findByWholePlanIndex(Long wholePlanIndex);
    List<WholePlan> findAllByWholePlanIndex(Long wholePlanIndex);
    List<WholePlan> findAllByMember(Member member);
    WholePlan findByMember(Member member);

}
