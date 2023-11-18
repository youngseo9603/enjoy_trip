package com.example.trip.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.sql.Timestamp;
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
    private Timestamp Date;

    @ManyToOne
    @JoinColumn(name = "planIndex")
    private Plan plan;

    @OneToMany(mappedBy = "planDay")
    private List<WholePlan> wholePlans;

}
