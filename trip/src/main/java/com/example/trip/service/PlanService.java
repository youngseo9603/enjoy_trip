package com.example.trip.service;

import com.example.trip.domain.Address;
import com.example.trip.domain.Member;
import com.example.trip.domain.WholePlan;
import com.example.trip.dto.Plan.CreateWholePlanRequest;
import com.example.trip.dto.Plan.Plan;
import com.example.trip.dto.Plan.PlanDay;
import com.example.trip.repository.MemberRepository;
import com.example.trip.repository.PlanDayRepository;
import com.example.trip.repository.PlanRepository;
import com.example.trip.repository.WholePlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanService {

    private final WholePlanRepository wholePlanRepository;
    private final MemberRepository memberRepository;
    private final PlanDayRepository planDayRepository;
    private final PlanRepository planRepository;


    //사용자 plan list 출력
    public List<WholePlan> findPlanListByMemberIndex(Long memberIndex){
        List<WholePlan> list = wholePlanRepository.findAllByMember_MemberIndex(memberIndex);
        return list;
    }

    public WholePlan findWholePlanByWholePlanIndex(Long wholePlanIndex){
        WholePlan wholePlan = wholePlanRepository.findByWholePlanIndex(wholePlanIndex);
        return wholePlan;
    }

    public void createWholePlan(CreateWholePlanRequest request, Long memberIndex){

        WholePlan wholePlan = WholePlan.builder()
                .title(request.getTitle())
                .member(memberRepository.findByMemberIndex(memberIndex))
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

        wholePlanRepository.save(wholePlan);

        for(PlanDay planDay : request.getPlanDays()){
            com.example.trip.domain.PlanDay pd = com.example.trip.domain.PlanDay.builder()
                    .Date(planDay.getDate())
                    .wholePlan(wholePlan)
                    .build();
            planDayRepository.save(pd);

            for(Plan plan : planDay.getPlans()){
                com.example.trip.domain.Plan p = com.example.trip.domain.Plan.builder()
                        .address(plan.getAddress())
                        .placeName(plan.getPlaceName())
                        .category(plan.getCategory())
                        .orders(plan.getOrder())
                        .planDay(pd)
                        .build();
                planRepository.save(p);
            }
        }

    }


}
