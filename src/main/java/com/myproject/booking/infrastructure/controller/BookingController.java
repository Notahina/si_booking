package com.myproject.booking.infrastructure.controller;

import com.myproject.booking.application.dto.BookingResponse;
import com.myproject.booking.application.service.IBookingService;
import com.myproject.booking.domain.Booking;
import com.myproject.booking.infrastructure.mapper.BookingMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {
    private  final IBookingService bookingService;

    public BookingController(IBookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/booking/{idBooking}")
    public BookingResponse findBooking(@PathVariable(name = "idBooking") Integer idBooking){
        Booking booking = bookingService.findById(idBooking);

        return BookingMapper.toBookingResponse(booking);
    }
}
