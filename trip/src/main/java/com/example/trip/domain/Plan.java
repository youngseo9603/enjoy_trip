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

    @Column(name = "startTime")
    private Timestamp startTime;

    @Column(name = "endTime")
    private Timestamp endTime;

    @ManyToOne
    @JoinColumn(name = "placeIndex")
    private Place place;

}