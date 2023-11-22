package com.example.trip.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="Wish")
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wish {
    @Id
    @GeneratedValue
    @Column(name = "wishIndex")
    private Long wishIndex;

    @ManyToOne
    @JoinColumn(name = "memberIndex")
    private Member member;

    @Embedded
    private Address address;

    @Column(name = "placeName")
    private String placeName;

    @Column(name = "category")
    private String category;
}
