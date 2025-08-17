package com.myproject.booking.application.dto;

import com.myproject.booking.domain.BookingStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class BookingRequest {
    private String idResource;
    private Integer idBooking;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private BookingStatus status;
}
