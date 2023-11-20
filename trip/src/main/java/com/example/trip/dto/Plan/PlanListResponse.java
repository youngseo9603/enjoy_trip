package com.example.trip.dto.Plan;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PlanListResponse {

    private int pageNum;
    private List<PlanInfo> planInfo;

}
