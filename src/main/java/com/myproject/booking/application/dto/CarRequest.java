package com.myproject.booking.application.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class CarRequest extends ResourceRequest{
    private String marque;
    private String model;
    private String color;
    private String year;
    private Double dailyPrice;
    private String matricule;
    private String  transmission;
    private String fuelType;
    private int numberOfSeats = 5;

    public CarRequest() {
        this.setResourceType("car");
    }
}
