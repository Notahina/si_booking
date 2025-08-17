package com.myproject.booking.domain.resource;

import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
public class Room extends Resource {


    @Override
    public double calculatePrice(LocalDateTime start, LocalDateTime end) {
        return 0;
    }
}
