package com.myproject.booking.infrastructure.controller;

import com.myproject.booking.application.dto.BookingRequest;
import com.myproject.booking.application.dto.BookingResponse;
import com.myproject.booking.application.dto.ErrorResponseDTO;
import com.myproject.booking.application.exception.BusinessException;
import com.myproject.booking.application.service.IBookingService;
import com.myproject.booking.domain.Booking;
import com.myproject.booking.infrastructure.mapper.BookingMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/booking/")
    public ResponseEntity<?> createBooking(@RequestBody BookingRequest bookingRequest)  {
        try{
            Booking reserve = bookingService.reserveBooking(bookingRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(BookingMapper.toBookingResponse(reserve));
        }catch (BusinessException e){
            ErrorResponseDTO errorResponse = new ErrorResponseDTO();
            errorResponse.setMessage(e.getMessage());
            errorResponse.setStatus(400);
            return ResponseEntity.badRequest().body(errorResponse);
        }

    }
}
