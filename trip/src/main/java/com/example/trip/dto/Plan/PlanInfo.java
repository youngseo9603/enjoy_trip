package com.example.trip.dto.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.sql.Timestamp;
import java.time.LocalDate;

@Data @AllArgsConstructor
public class PlanInfo {

    private Long wholePlanIndex;
    private String title;
    private LocalDate startTime;
    private LocalDate endTime;

}