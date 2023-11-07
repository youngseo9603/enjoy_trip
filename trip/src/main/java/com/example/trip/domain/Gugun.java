package com.example.trip.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "gugun", schema = "enjoy_trip_db")
public class Gugun {
    @EmbeddedId
    private GugunId id;

    @Column(name = "gugun_name", length = 30)
    private String gugunName;

}