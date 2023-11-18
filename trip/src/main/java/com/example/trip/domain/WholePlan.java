package com.example.trip.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;

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
    private String startDate;

    @Column(name = "endDate")
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "wholePlanIndex")
    private MemberPlan memberPlans;

}
