package com.myproject.booking.application.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Builder
@Data
public class BookingResponse {
    private String idBooking;
    private String status;
    private String typeResource;
    private ResourceDetailResponse resourceDetail;
    private double price;
    private String dateStart;
    private String dateEnd;
}
