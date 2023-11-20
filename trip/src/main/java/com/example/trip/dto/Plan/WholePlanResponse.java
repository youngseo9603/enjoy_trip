package com.example.trip.dto.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
public class WholePlanResponse {

    private Long wholePlanIndex;
    private String title;
    private LocalDate startTime;
    private LocalDate endTime;
    private List<PlanDay> planDays;

}
