package com.example.trip.dto.Plan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class PlanDay {

    private Long playDayIndex;
    private LocalDate date;
    private List<Plan> plans;

}
