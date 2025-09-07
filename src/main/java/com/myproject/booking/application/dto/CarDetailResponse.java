package com.myproject.booking.application.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class CarDetailResponse extends ResourceDetailResponse{
    private String color;
    private String brand;
    private String model;
    private String year;
    private String fuelType;
    private String transmission;
    private double dailyPrice;
}
