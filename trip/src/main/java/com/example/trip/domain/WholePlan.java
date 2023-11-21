package com.example.trip.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
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
    private LocalDate startDate;

    @Column(name = "endDate")
    private LocalDate endDate;

    @Column(name = "Title")
    private String title;

    @OneToMany(mappedBy = "wholePlan")
    private List<PlanDay> planDays;

    @ManyToOne
    @JoinColumn(name = "memberIndex")
    private Member member;

}
