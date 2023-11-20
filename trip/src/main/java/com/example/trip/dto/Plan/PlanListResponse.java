package com.example.trip.dto.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@AllArgsConstructor
public class PlanListResponse {

    private int pageNum;
    private List<PlanInfo> planInfo;

}
