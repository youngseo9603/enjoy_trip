package com.example.trip.dto.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Data @AllArgsConstructor
public class CreateWholePlanRequest {

    private String title;
    private Timestamp startDate;
    private Timestamp endDate;
    private List<PlanDay> planDays;

}
