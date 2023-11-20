package com.example.trip.dto.Plan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class PlanDay {

    private Long playDayIndex;
    private Timestamp date;
    private List<Plan> plans;

}
