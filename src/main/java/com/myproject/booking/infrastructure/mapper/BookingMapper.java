package com.myproject.booking.infrastructure.mapper;

import com.myproject.booking.application.dto.BookingResponse;
import com.myproject.booking.application.dto.ResourceDetailResponse;
import com.myproject.booking.application.utils.DateUtils;
import com.myproject.booking.application.utils.mapper.ResourceMapper;
import com.myproject.booking.domain.Booking;
import com.myproject.booking.domain.resource.Resource;
import com.myproject.booking.domain.valueObject.Periode;
import com.myproject.booking.infrastructure.entity.BookingEntity;
import org.springframework.stereotype.Component;


@Component
public class BookingMapper {

    public static BookingResponse toBookingResponse(Booking booking) {
        ResourceDetailResponse resourceDetailResponse = ResourceMapper.toResponse(booking.getResource());

        return BookingResponse.builder()
                .idBooking(booking.getId().toString())
                .price(booking.getTotalPrice())
                .resourceDetail(resourceDetailResponse)
                .typeResource(booking.getResource().getTypeResource().toLowerCase())
                .dateStart(DateUtils.formatDateTime(booking.getPeriode().getStartDate()))
                .dateEnd(DateUtils.formatDateTime(booking.getPeriode().getEndDate()))
                .build();
    }




    public static Booking toDomain(BookingEntity entity,Resource resource) {
        Booking booking = new Booking();
        booking.setId(entity.getBookingId());
        booking.setPeriode(new Periode(entity.getStartDate(), entity.getEndDate()));
        booking.setResource(resource);
        booking.setTotalPrice(entity.getPrice());
        return booking;
    }
}
