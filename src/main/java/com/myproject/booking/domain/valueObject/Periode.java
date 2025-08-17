package com.myproject.booking.domain.valueObject;

import lombok.Getter;

import java.time.LocalDateTime;

public class Periode {
    @Getter
    private final LocalDateTime startDate;
    @Getter
    private final LocalDateTime endDate;

    public Periode(LocalDateTime startDate, LocalDateTime endDate) {

        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("startDate and endDate may not be null");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("La date debut doit etre avant la date fin");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public boolean overlaps(Periode other) {
        return !this.endDate.isBefore(other.getStartDate()) && !this.startDate.isAfter(other.getEndDate());
    }
}
