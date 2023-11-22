package com.example.trip.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="wish")
@Getter
@Setter
@Data
public class Wish {
    @Id
    @GeneratedValue
    @Column(name = "wishIndex")
    private Long wishIndex;

    @OneToOne
    @JoinColumn(name = "memberIndex")
    private Member member;

    @Embedded
    private Address address;

    @Column(name = "placeName")
    private String placeName;

    @Column(name = "category")
    private String category;
}
