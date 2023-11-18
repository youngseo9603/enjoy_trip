package com.example.trip.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    String addr1;
    String addr2;
    String zipcode;
    String longtitude;

}
