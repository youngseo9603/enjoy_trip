package com.example.trip.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "PlanDay")
@Getter
@Setter
@Data
@ConstructorBinding
public class PlanDay {

    @Id
    @GeneratedValue
    @Column(name = "planDayIndex")
    private Long planDayIndex;


    @Column(name = "Date")
    private LocalDate Date;

    @JsonIgnore
    @OneToMany(mappedBy = "planDay", fetch = FetchType.EAGER)
    private List<Plan> plan;

    @ManyToOne
    @JoinColumn(name = "wholePlanIndex")
    private WholePlan wholePlan;

}
