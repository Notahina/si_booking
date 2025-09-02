package com.myproject.booking.application.service;

import com.myproject.booking.application.ddd.DomainService;
import com.myproject.booking.application.dto.BookingRequest;
import com.myproject.booking.application.exception.BusinessException;
import com.myproject.booking.application.repository.IBookingRepository;
import com.myproject.booking.application.repository.IResourceRepository;
import com.myproject.booking.domain.Booking;
import com.myproject.booking.domain.BookingStatus;
import com.myproject.booking.domain.resource.Resource;
import com.myproject.booking.domain.valueObject.Periode;
import org.springframework.util.CollectionUtils;

import java.util.List;

@DomainService
public class BookingService implements IBookingService{
    private final IResourceRepository resourceRepository;
    private final IBookingRepository bookingRepository;

    public BookingService(IResourceRepository resourceRepository, IBookingRepository bookingRepository) {
        this.resourceRepository = resourceRepository;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking findById(Integer id) {
        return bookingRepository.findByIdBooking(id);
    }

    @Override
    public Booking reserveBooking(BookingRequest request) throws BusinessException {
        Resource resource = resourceRepository.findById(request.getIdResource());
        validateRequestBooking(request, resource);

        Booking newBooking = new Booking();
        newBooking.setStatus(BookingStatus.RESERVED);
        newBooking.setResource(resource);
        newBooking.setPeriode(new Periode(request.getStartTime(), request.getEndTime()));
        newBooking.setTotalPrice( newBooking.getResource().calculatePrice(newBooking.getPeriode().getStartDate(), newBooking.getPeriode().getEndDate()));
        return bookingRepository.saveBooking(newBooking);
    }

    @Override
    public Booking cancelBooking(BookingRequest request) throws BusinessException {
        Resource resource = resourceRepository.findById(request.getIdResource());
        validateRequestBooking(request, resource);

        Booking booking = bookingRepository.findByIdBooking(request.getIdBooking());
        if (booking.getStatus() == BookingStatus.RESERVED) {
            booking.setStatus(BookingStatus.CANCELLED);
            booking.getResource().setAvailable(true);

            bookingRepository.saveBooking(booking);
            return booking;
        }
        throw new BusinessException("Booking cannot cancel.");
    }

    @Override
    public Booking updateBooking(BookingRequest request) throws BusinessException {
        Booking booking = bookingRepository.findByIdBooking(request.getIdBooking());
        Periode periode = new Periode(request.getStartTime(), request.getEndTime());
        Resource resource = resourceRepository.findById(request.getIdResource());

        boolean canUpdate = booking.getResource().getTypeResource().equals(resource.getTypeResource());
        if (!canUpdate)
            throw new BusinessException("Pour modifier la réservation #%s , elle doit etre du meme type que %s".formatted(request.getIdResource(), booking.getResource().getTypeResource()));

        //test is resource est  libre pour la periode demander
        List<Booking> bookingList = bookingRepository.findResourceIdInPeriode(request.getIdResource(), periode);
        if (!CollectionUtils.isEmpty(bookingList))
            throw new BusinessException("Une réservation à été déja fait pour cette date du "+periode.getStartDate() + " au " +periode.getEndDate());

        booking.setStatus(BookingStatus.RESERVED);
        booking.setPeriode(new Periode(request.getStartTime(), request.getEndTime()));
        return bookingRepository.saveBooking(booking);
    }


    @Override
    public Booking processBookingRequest(BookingRequest bookingRequest) throws BusinessException {
        return switch (bookingRequest.getStatus()){
            case RESERVED , FINISHED -> null;
            case CANCELLED -> cancelBooking(bookingRequest);
        };
    }

    private void validateRequestBooking(BookingRequest request, Resource resource) throws BusinessException {

        if (request.getStatus().equals(BookingStatus.CANCELLED)){
            if (request.getIdBooking() == null || request.getIdBooking() <= 0){
                throw new BusinessException("No booking id");
            }
        }

        if (request.getStatus().equals(BookingStatus.RESERVED)){
            if (request.getStartTime() == null || request.getEndTime() == null){
                throw new BusinessException("");
            }
            if (request.getEndTime().isBefore(request.getStartTime())){
                throw new BusinessException("");
            }
            if (resource == null)
                throw new BusinessException("Resource not found");

            if (!resource.isAvailable())
                throw new BusinessException(resource.errorMessageAvailable());
        }
    }
 }

