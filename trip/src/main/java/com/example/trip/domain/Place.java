package com.example.trip.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Place")
@Getter
@Setter
@Data
@ConstructorBinding
public class Place {

    @Id
    @GeneratedValue
    @Column(name = "placeIndex")
    private Long placeIndex;

    @Embedded
    private Address address;

    private String placeName;

    private String category;

    @OneToMany(mappedBy = "place")
    private List<Plan> plans;

}
