package com.example.trip.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Plan")
@Getter
@Setter
@Data
@ConstructorBinding
public class Plan {

    @Id
    @GeneratedValue
    @Column(name = "planIndex")
    private Long planIndex;

    @Embedded
    private Address address;

    @Column(name = "placeName")
    private String placeName;

    @Column(name = "category")
    private String category;

    @Column(name = "orders")
    private int orders;

    @ManyToOne
    @JoinColumn(name = "planDayIndex")
    private PlanDay planDay;

}
