package com.myproject.booking.infrastructure.repository;

import com.myproject.booking.application.repository.IBookingRepository;
import com.myproject.booking.application.repository.IResourceRepository;
import com.myproject.booking.domain.Booking;
import com.myproject.booking.domain.resource.Resource;
import com.myproject.booking.domain.valueObject.Periode;
import com.myproject.booking.infrastructure.entity.BookingEntity;
import com.myproject.booking.infrastructure.mapper.BookingMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookingRepositoryImpl implements IBookingRepository {
    
    private final BookingEntityRepository  bookingEntityRepository;
    private final IResourceRepository  resourceRepository;

    public BookingRepositoryImpl(BookingEntityRepository bookingEntityRepository, IResourceRepository resourceRepository) {
        this.bookingEntityRepository = bookingEntityRepository;
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Booking saveBooking(Booking booking) {
        return null;
    }

    @Override
    public Booking findByIdBooking(Integer id) {
        BookingEntity booking = bookingEntityRepository.findById(id).orElse(null);
        if (booking == null) {
            return null;
        }
        Resource resource = resourceRepository.findById(String.valueOf(id));
        return BookingMapper.toDomain(booking, resource);
    }

    @Override
    public List<Booking> findResourceIdInPeriode(String idResource, Periode periode) {
        return List.of();
    }
}
