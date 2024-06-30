package com.personal.InventionMarket.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class Address {
    private String country;
    private String state;
    private String city;
    private String pincode;
    private String area;
    private String houseno;
}
