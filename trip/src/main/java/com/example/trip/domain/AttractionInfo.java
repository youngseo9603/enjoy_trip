package com.example.trip.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "attraction_info", schema = "enjoy_trip_db")
public class AttractionInfo {
    @Id
    @Column(name = "content_id", nullable = false)
    private Integer id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "addr1", length = 100)
    private String addr1;

    @Column(name = "addr2", length = 50)
    private String addr2;

    @Column(name = "zipcode", length = 50)
    private String zipcode;

    @Column(name = "tel", length = 50)
    private String tel;

    @Column(name = "first_image", length = 200)
    private String firstImage;

    @Column(name = "first_image2", length = 200)
    private String firstImage2;

    @Column(name = "readcount")
    private Integer readcount;

    @Column(name = "latitude", precision = 20, scale = 17)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 20, scale = 17)
    private BigDecimal longitude;

    @Column(name = "mlevel", length = 2)
    private String mlevel;

}