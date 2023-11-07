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
@Table(name = "attraction_detail", schema = "enjoy_trip_db")
public class AttractionDetail {
    @Id
    @Column(name = "content_id", nullable = false)
    private Integer id;

    @Column(name = "cat1", length = 3)
    private String cat1;

    @Column(name = "cat2", length = 5)
    private String cat2;

    @Column(name = "cat3", length = 9)
    private String cat3;

    @Column(name = "created_time", length = 14)
    private String createdTime;

    @Column(name = "modified_time", length = 14)
    private String modifiedTime;

    @Column(name = "booktour", length = 5)
    private String booktour;

}