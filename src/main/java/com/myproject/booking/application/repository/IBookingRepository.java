package com.myproject.booking.application.repository;

import com.myproject.booking.domain.Booking;
import com.myproject.booking.domain.valueObject.Periode;

import java.util.List;

public interface IBookingRepository {
    Booking saveBooking(Booking booking);

    Booking findByIdBooking(Integer id);

    List<Booking> findResourceIdInPeriode(String idResource, Periode periode);
}
