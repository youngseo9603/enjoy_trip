package com.example.trip.dto.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class WholePlanResponse {

    private Long wholePlanIndex;
    private String title;
    private Timestamp startTime;
    private Timestamp endTime;
    private List<PlanDay> planDays;

}
