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
import java.util.Set;

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
        BookingEntity bookingEntity = new BookingEntity();
        if (booking.getId() != null) {
             bookingEntity = bookingEntityRepository.findById(booking.getId()).orElse(new BookingEntity());
        }

        bookingEntity.setPrice(booking.getTotalPrice());
        bookingEntity.setResourceId(Integer.valueOf(booking.getResource().getIdResource()));
        bookingEntity.setStartDate(booking.getPeriode().getStartDate());
        bookingEntity.setEndDate(booking.getPeriode().getEndDate());
        bookingEntity.setBookingStatus(booking.getStatus());
        BookingEntity saveBooking = bookingEntityRepository.save(bookingEntity);

        Resource resource = resourceRepository.findById(String.valueOf(saveBooking.getResourceId()));
        return BookingMapper.toDomain(saveBooking,resource);
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
        Set<BookingEntity> bookingEntities = bookingEntityRepository.findOverlappingBookings(Integer.valueOf(idResource),periode.getStartDate(),periode.getEndDate());

        return bookingEntities.stream().map(
                bookingEntity -> {
                    return BookingMapper.toDomain(bookingEntity, resourceRepository.findById(String.valueOf(bookingEntity.getResourceId())));
                }
        ).toList();
    }
}
