package com.myproject.booking.infrastructure.repository;

import com.myproject.booking.application.repository.IBookingRepository;
import com.myproject.booking.domain.Booking;
import com.myproject.booking.domain.valueObject.Periode;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingRepositoryImpl implements IBookingRepository {
    @Override
    public Booking saveBooking(Booking booking) {
        return null;
    }

    @Override
    public Booking findByIdBooking(Integer id) {
        return null;
    }

    @Override
    public List<Booking> findResourceIdInPeriode(String idResource, Periode periode) {
        return List.of();
    }
}
