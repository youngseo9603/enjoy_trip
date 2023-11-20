package com.example.trip.controller;

import com.example.trip.controller.constant.Message;
import com.example.trip.controller.constant.StatusCode;
import com.example.trip.domain.WholePlan;
import com.example.trip.dto.Board.ListBoardResponse;
import com.example.trip.dto.Plan.PlanListResponse;
import com.example.trip.repository.WholePlanRepository;
import com.example.trip.service.MemberService;
import com.example.trip.service.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.util.List;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final WholePlanRepository wholePlanRepository;

    @GetMapping("/list")
    public ResponseEntity<?> getPlanList(@RequestBody Long memberIndex){
        PlanListResponse planListResponse = planService.findPlanListByMemberIndex(memberIndex);
        Message message = new Message(StatusCode.OK, "내 여행 목록 조회 성공", planListResponse);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{wholePlanIndex}")
    public ResponseEntity<?> getWholePlan(@RequestHeader("wholePlanIndex") Long wholePlanIndex,@RequestBody Long memberIndex){
        WholePlan wholePlan = planService.findWholePlanByWholePlanIndex(wholePlanIndex);
        Message message = new Message(StatusCode.OK, "내 여행 목록 조회 성공", wholePlan);
        return ResponseEntity.ok(message);
    }




}
