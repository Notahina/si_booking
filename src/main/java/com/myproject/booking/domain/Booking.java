package com.myproject.booking.domain;

import com.myproject.booking.domain.resource.Car;
import com.myproject.booking.domain.resource.Resource;
import com.myproject.booking.domain.valueObject.Periode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Booking {
    private Integer id;
    private Resource resource;
    private Periode periode;
    private Double totalPrice;
    private BookingStatus status;
}
