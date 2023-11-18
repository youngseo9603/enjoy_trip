package com.example.trip.domain;

import lombok.*;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.persistence.*;

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

    @Column(name  = "placeName")
    private String placeName;

    @Column(name = "category")
    private String category;
}
