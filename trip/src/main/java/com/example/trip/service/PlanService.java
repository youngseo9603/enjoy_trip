package com.example.trip.service;

import com.example.trip.domain.Member;
import com.example.trip.domain.MemberPlan;
import com.example.trip.domain.WholePlan;
import com.example.trip.dto.Plan.PlanInfo;
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


    //사용자 plan list 출력
    public PlanListResponse findPlanListByMemberIndex(Long memberIndex){
        List<PlanInfo> planInfos = new ArrayList<>();

        List<MemberPlan> memberPlans = memberPlanRepository.findAllByMember_MemberIndex(memberIndex);

        for(MemberPlan m : memberPlans){
            WholePlan wholePlan = m.getWholePlan();
            planInfos.add(new PlanInfo(wholePlan.getWholePlanIndex(), wholePlan.getTitle(), wholePlan.getStartDate(), wholePlan.getEndDate()));
        }

        PlanListResponse planListResponse = new PlanListResponse(1, planInfos);

        return planListResponse;
    }

}
