package com.example.trip.service;

import com.example.trip.domain.Member;
import com.example.trip.domain.MemberPlan;
import com.example.trip.dto.Plan.PlanListResponse;
import com.example.trip.repository.MemberPlanRepository;
import com.example.trip.repository.MemberRepository;
import com.example.trip.repository.WholePlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanService {

    private final WholePlanRepository wholePlanRepository;
    private final MemberPlanRepository memberPlanRepository;

//    public List<PlanListResponse> findPlanListByMemberIndex(Long memberIndex){
//        List<MemberPlan> memberPlans = new ArrayList<>();
//
//        return 0;
//    }

}
