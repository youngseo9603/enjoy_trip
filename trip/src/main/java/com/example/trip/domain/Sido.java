package com.example.trip.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "sido", schema = "enjoy_trip_db")
public class Sido {
    @Id
    @Column(name = "sido_code", nullable = false)
    private Integer id;

    @Column(name = "sido_name", length = 30)
    private String sidoName;

}