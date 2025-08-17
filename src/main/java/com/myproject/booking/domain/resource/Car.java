package com.myproject.booking.domain.resource;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Data
public class Car extends Resource {
    private String marque;
    private String model;
    private String color;
    private String year;
    private String licensePlate;
    private Transmission transmission;
    private FuelType fuelType;
    private int numberOfSeats;
    private double dailyRate;


    @Override
    public double calculatePrice(LocalDateTime start, LocalDateTime end) {
        long days = ChronoUnit.DAYS.between(start, end);
        long roundedDays = days  == 0 ? 1 : days;
        return dailyRate * roundedDays;
    }

    @Override
    public String errorMessageAvailable(){
        return "La voiture est occupé";
    }

    @Override
    public String errorMessageCanceled(){
        return "La voiture n'est pas encore occupé ne peut pas etre annulé";
    }


}
