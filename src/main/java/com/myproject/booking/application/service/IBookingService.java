package com.myproject.booking.application.service;

import com.myproject.booking.application.dto.BookingRequest;
import com.myproject.booking.application.exception.BusinessException;
import com.myproject.booking.domain.Booking;

public interface IBookingService {

    Booking findById(Integer id);
    Booking reserveBooking(BookingRequest request) throws BusinessException;
    Booking cancelBooking(BookingRequest request) throws BusinessException;
    Booking updateBooking(BookingRequest request) throws BusinessException;
    Booking processBookingRequest(BookingRequest bookingRequest) throws BusinessException;
}
