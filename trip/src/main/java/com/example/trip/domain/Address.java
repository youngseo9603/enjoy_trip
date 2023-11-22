package com.example.trip.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Convert;
import javax.persistence.Embeddable;
import java.util.List;
import java.util.SimpleTimeZone;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    private String addr1;
    private String addr2;
    private String latitude;
    private String longitude;

}
