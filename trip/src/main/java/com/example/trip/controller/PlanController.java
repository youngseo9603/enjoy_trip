package com.example.trip.controller;

import com.example.trip.controller.constant.Message;
import com.example.trip.controller.constant.StatusCode;
import com.example.trip.domain.WholePlan;
import com.example.trip.dto.Board.ListBoardResponse;
import com.example.trip.dto.Plan.*;
import com.example.trip.dto.WholePlanAndWishListRequest;
import com.example.trip.repository.WholePlanRepository;
import com.example.trip.service.MemberService;
import com.example.trip.service.PlanService;
import com.example.trip.service.WishService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/plan")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService;
    private final WishService wishService;

    @GetMapping("/list")
    public ResponseEntity<?> getPlanList(@RequestParam Long memberIndex){
        List<PlanInfo> planInfos = planService.findPlanListByMemberIndex(memberIndex);
        Message message = new Message(StatusCode.OK, "내 여행 목록 조회 성공", planInfos);
        return ResponseEntity.ok(message);
    }

    @GetMapping()
    public ResponseEntity<?> getWholePlan(@RequestParam Long wholePlanIndex){
        CreateWholePlanRequest wholePlan = planService.findWholePlanByWholePlanIndex(wholePlanIndex);
        Message message = new Message(StatusCode.OK, "내 여행 목록 조회 성공", wholePlan);
        return ResponseEntity.ok(message);
    }

    @PostMapping()
    public ResponseEntity<?> createWholePlan(@RequestParam Long memberIndex, @RequestBody WholePlanAndWishListRequest request){
        planService.createWholePlan(request.getCreateWholePlanRequest(), memberIndex);
        wishService.createWishList(memberIndex, request.getWishRequests());
        Message message = new Message(StatusCode.OK, "여행 계획 등록 성공 및 위시리스트 수정 성공");
        return ResponseEntity.ok(message);
    }

    @PutMapping()
    public ResponseEntity<?> modifyWholePlan(@RequestParam Long memberIndex, @RequestParam Long wholePlanIndex, @RequestBody CreateWholePlanRequest createWholePlanRequest){
        planService.updateWholePlan(memberIndex, wholePlanIndex, createWholePlanRequest);
        Message message = new Message(StatusCode.OK, "여행 계획 수정 성공");
        return ResponseEntity.ok(message);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteWholePlan(@RequestParam Long memberIndex, @RequestParam Long wholePlanIndex){
        planService.removeWholePlan(wholePlanIndex);
        Message message = new Message(StatusCode.OK, "여행 계획 삭제 성공");
        return ResponseEntity.ok(message);
    }

    @GetMapping("/date")
    public ResponseEntity<?> getPlanDayByPlanDayIndex(@RequestParam Long wholePlanIndex, @RequestParam Long planDayIndex){
        PlanDay planDay = planService.readPlanByPlanDate(wholePlanIndex, planDayIndex);
        Message message = new Message(StatusCode.OK, "여행 계획 날짜별 조회 성공", planDay);
        return ResponseEntity.ok(message);
    }

    @PutMapping("/date")
    public ResponseEntity<?> modifyPlanDayByPlanDayIndex(@RequestParam Long wholePlanIndex, @RequestParam Long planDayIndex, @RequestBody PlanDay planDay){
        Message message;
        if(planService.updatePlanDay(wholePlanIndex, planDay))
            message = new Message(StatusCode.OK, "여행 계획 날짜별 수정 성공");
        else
            message = new Message(StatusCode.BAD_REQUEST, "해당하는 planDay를 찾을 수 없습니다.");
        return ResponseEntity.ok(message);
    }

}
