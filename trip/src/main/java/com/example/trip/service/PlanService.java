package com.example.trip.service;

import com.example.trip.domain.Address;
import com.example.trip.domain.Member;
import com.example.trip.domain.WholePlan;
import com.example.trip.dto.Plan.CreateWholePlanRequest;
import com.example.trip.dto.Plan.Plan;
import com.example.trip.dto.Plan.PlanDay;
import com.example.trip.dto.Plan.PlanInfo;
import com.example.trip.repository.MemberRepository;
import com.example.trip.repository.PlanDayRepository;
import com.example.trip.repository.PlanRepository;
import com.example.trip.repository.WholePlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.DateFormat;
import java.time.LocalDate;
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
    public List<PlanInfo> findPlanListByMemberIndex(Long memberIndex){
        Member member = memberRepository.findByMemberIndex(memberIndex);
        List<WholePlan> list = wholePlanRepository.findAllByMember(member);
        List<PlanInfo> response = new ArrayList<>();
        for(WholePlan wholePlan: list){
            PlanInfo planInfo = PlanInfo.builder()
                    .wholePlanIndex(wholePlan.getWholePlanIndex())
                    .title(wholePlan.getTitle())
                    .startTime(wholePlan.getStartDate())
                    .endTime(wholePlan.getEndDate())
                    .build();
            response.add(planInfo);
        }
        return response;
    }

    public CreateWholePlanRequest findWholePlanByWholePlanIndex(Long wholePlanIndex){
        WholePlan wholePlan = wholePlanRepository.findByWholePlanIndex(wholePlanIndex);
        List<PlanDay> planDays = new ArrayList<>();

        for(com.example.trip.domain.PlanDay planDay : wholePlan.getPlanDays()){
            List<Plan> plans = new ArrayList<>() ;
            for(com.example.trip.domain.Plan plan : planDay.getPlan()){
                Plan p = Plan.builder()
                        .planIndex(plan.getPlanIndex())
                        .placeName(plan.getPlaceName())
                        .category(plan.getCategory())
                        .orders(plan.getOrders())
                        .address(plan.getAddress())
                        .build();
                plans.add(p);
            }
            PlanDay pd = PlanDay.builder()
                    .date(planDay.getDate())
                    .playDayIndex(planDay.getPlanDayIndex())
                    .plans(plans)
                    .build();
            planDays.add(pd);
        }

        CreateWholePlanRequest response = CreateWholePlanRequest.builder()
                .title(wholePlan.getTitle())
                .startDate(wholePlan.getStartDate())
                .endDate(wholePlan.getEndDate())
                .planDays(planDays)
                .build();

        return response;
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
                        .orders(plan.getOrders())
                        .planDay(pd)
                        .build();
                planRepository.save(p);
            }
        }

    }

    @Transactional
    public void updateWholePlan(Long memberindex, Long wholePlanIndex, CreateWholePlanRequest createWholePlanRequest){
        WholePlan wholePlan = wholePlanRepository.findByWholePlanIndex(wholePlanIndex);

        for(com.example.trip.domain.PlanDay planDay : wholePlan.getPlanDays()){
            for(com.example.trip.domain.Plan plan : planDay.getPlan()){
                planRepository.delete(plan);
            }
            planDayRepository.delete(planDay);
        }

        wholePlan.setTitle(createWholePlanRequest.getTitle());
        wholePlan.setEndDate(createWholePlanRequest.getEndDate());
        wholePlan.setStartDate(createWholePlanRequest.getStartDate());

        for(PlanDay planDay : createWholePlanRequest.getPlanDays()){
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
                        .orders(plan.getOrders())
                        .planDay(pd)
                        .build();
                planRepository.save(p);
            }
        }

        wholePlanRepository.save(wholePlan);
    }

    @Transactional
    public void removeWholePlan(Long wholePlanIndex){
        WholePlan wholePlan = wholePlanRepository.findByWholePlanIndex(wholePlanIndex);
        for(com.example.trip.domain.PlanDay planDay : wholePlan.getPlanDays()){
            for(com.example.trip.domain.Plan plan : planDay.getPlan()){
                planRepository.delete(plan);
            }
            planDayRepository.delete(planDay);
        }
        wholePlanRepository.delete(wholePlan);
    }

    //need to fixed
    public PlanDay readPlanByPlanDate(Long wholePlanIndex, Long planDayIndex){
        WholePlan wholePlan = wholePlanRepository.findByWholePlanIndex(wholePlanIndex);

        PlanDay pd = new PlanDay();

        for(com.example.trip.domain.PlanDay planDay : wholePlan.getPlanDays()){
            if(planDay.getPlanDayIndex() == planDayIndex) {
                pd = PlanDay.builder()
                        .date(planDay.getDate())
                        .playDayIndex(planDayIndex)
                        .plans(new ArrayList<>())
                        .build();

                for(com.example.trip.domain.Plan plan : planDay.getPlan()){
                    Plan p = Plan.builder()
                            .planIndex(plan.getPlanIndex())
                            .address(plan.getAddress())
                            .placeName(plan.getPlaceName())
                            .category(plan.getCategory())
                            .orders(plan.getOrders())
                            .build();
                    pd.getPlans().add(p);
                }
            }
        }
        return pd;
    }

    @Transactional
    public void updatePlanDay(Long wholePlanIndex, PlanDay planDaydto){
        com.example.trip.domain.PlanDay planDay = planDayRepository.findByPlanDayIndex(planDaydto.getPlayDayIndex());

        for(com.example.trip.domain.Plan plan : planDay.getPlan()){
            planRepository.delete(plan);
        }
        planDayRepository.delete(planDay);

//        com.example.trip.domain.PlanDay pd = com.example.trip.domain.PlanDay.builder()
//                .wholePlan(wholePlanRepository.findByWholePlanIndex(wholePlanIndex))
//                .planDayIndex(planDaydto.getPlayDayIndex())
//                .Date(planDaydto.getDate())
//                .build();
//        planDayRepository.save(pd);
//
//        for(Plan plan : planDaydto.getPlans()){
//            com.example.trip.domain.Plan p = com.example.trip.domain.Plan.builder()
//                    .planIndex(plan.getPlanIndex())
//                    .address(plan.getAddress())
//                    .placeName(plan.getPlaceName())
//                    .category(plan.getCategory())
//                    .orders(plan.getOrders())
//                    .planDay(pd)
//                    .build();
//            planRepository.save(p);
//        }

    }

}
