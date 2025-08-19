package com.myproject.booking.application.service;

import com.myproject.booking.application.dto.BookingRequest;
import com.myproject.booking.application.exception.BusinessException;
import com.myproject.booking.application.repository.stub.BookingRepositoryStub;
import com.myproject.booking.application.repository.IBookingRepository;
import com.myproject.booking.application.repository.IResourceRepository;
import com.myproject.booking.application.repository.stub.ResourceRepositoryStub;
import com.myproject.booking.application.utils.DateUtils;
import com.myproject.booking.domain.Booking;
import com.myproject.booking.domain.BookingStatus;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;

public class BookingServiceTest {

    private IBookingService bookingService;
    @BeforeEach
    public void setUp(){
        IResourceRepository resourceRepository = new ResourceRepositoryStub();
        IBookingRepository bookingRepository = new BookingRepositoryStub();
        bookingService = new BookingService(resourceRepository, bookingRepository);
    }

    @Test
    public void testCancelBooking() throws BusinessException {
        BookingRequest  bookingRequest = BookingRequest.builder()
                .idResource("CAR-01")
                .idBooking(2)
                .status(BookingStatus.CANCELLED)
                .build();

        Booking booking =bookingService.cancelBooking(bookingRequest);
        Assertions.assertTrue(booking.getResource().isAvailable());
        Assertions.assertNotNull(booking);
        Assertions.assertEquals(BookingStatus.CANCELLED, booking.getStatus());
    }

    @Test
    void testReseveNewBooking() throws BusinessException {
        BookingRequest bookingRequest = BookingRequest.builder()
                .idResource("CAR-02")
                .startTime(LocalDateTime.now())
                .endTime(LocalDateTime.now().plusDays(5))
                .status(BookingStatus.RESERVED)
                .build();

        Booking booking = bookingService.reserveBooking(bookingRequest);
    }

    @Test
    void testUpdateBooking_OK() throws BusinessException {
        LocalDateTime dateStart = DateUtils.parseDateTime("2025-08-07 14:30");

        BookingRequest request = BookingRequest.builder()
                .idBooking(2)
                .idResource("CAR-01")
                .startTime(dateStart.plusDays(10))
                .endTime(dateStart.plusDays(15))
                .build();

        Booking booking = bookingService.updateBooking(request);
    }

    @Test
    void testUpdateBooking_Exception()  {
        LocalDateTime dateStart = DateUtils.parseDateTime("2025-08-07 14:30");

        BookingRequest request = BookingRequest.builder()
                .idBooking(2)
                .idResource("CAR-01")
                .startTime(dateStart)
                .endTime(dateStart.plusDays(15))
                .build();

        Exception exception = Assertions.assertThrows(BusinessException.class, () -> {
            bookingService.updateBooking(request);
        });

        Assertions.assertTrue(exception.getMessage().contains("réservation"));
    }

}
