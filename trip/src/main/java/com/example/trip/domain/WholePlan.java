package com.example.trip.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "wholePlan")
@Getter
@Setter
@Data
@ConstructorBinding
public class WholePlan {

    @Id
    @GeneratedValue
    @Column(name = "wholePlanIndex")
    private Long wholePlanIndex;

    @Column(name = "startDate")
    private Timestamp startDate;

    @Column(name = "endDate")
    private Timestamp endDate;

    @Column(name = "Title")
    private String title;

    @ManyToOne
    @JoinColumn(name = "planDayIndex")
    private PlanDay planDay;

    @OneToMany(mappedBy = "wholePlan")
    private List<MemberPlan> memberPlans;

}
