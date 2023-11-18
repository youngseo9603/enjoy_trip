package com.example.trip.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Plan")
@Getter
@Setter
@Data
@ConstructorBinding
public class PlanDay {

    @Id
    @GeneratedValue
    @Column(name = "planDayIndex")
    private Long planDayIndex;

    

}
