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
@Table(name = "attraction_description", schema = "enjoy_trip_db")
public class AttractionDescription {
    @Id
    @Column(name = "content_id", nullable = false)
    private Integer id;

    @Column(name = "homepage", length = 100)
    private String homepage;

    @Column(name = "overview", length = 10000)
    private String overview;

    @Column(name = "telname", length = 45)
    private String telname;

}