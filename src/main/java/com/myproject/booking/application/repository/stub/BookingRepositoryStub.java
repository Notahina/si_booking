package com.myproject.booking.application.repository.stub;

import com.myproject.booking.application.ddd.Stub;
import com.myproject.booking.application.repository.IBookingRepository;
import com.myproject.booking.domain.Booking;
import com.myproject.booking.domain.BookingStatus;
import com.myproject.booking.domain.valueObject.Periode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Stub
public class BookingRepositoryStub implements IBookingRepository {

    private final List<Booking> bookingList;

    public BookingRepositoryStub() {
        String date ="2025-08-07 14:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(date, formatter);

        bookingList = new ArrayList<>();
        Periode periode = new Periode(dateTime, dateTime.plusDays(6));
        Booking booking1 = new Booking(1,ResourceRepositoryStub.getResources().get(0), periode, 0d,BookingStatus.RESERVED);
        booking1.getResource().setAvailable(false);

        Periode periode1 = new Periode(dateTime, LocalDateTime.now().plusDays(8));
        Booking booking2 = new Booking(2, ResourceRepositoryStub.getResources().get(2), periode1, 0d, BookingStatus.RESERVED );


        bookingList.add(booking1);
        bookingList.add(booking2);
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return null;
    }

    @Override
    public Booking findByIdBooking(Integer id) {
        return bookingList.stream().filter(booking -> booking.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<Booking> findResourceIdInPeriode(String idResource, Periode periode) {
        return bookingList.stream()
                .filter(booking -> booking.getResource().getIdResource().equalsIgnoreCase(idResource))
                .filter(booking -> booking.getPeriode().overlaps(periode))
                .toList();
    }
}
