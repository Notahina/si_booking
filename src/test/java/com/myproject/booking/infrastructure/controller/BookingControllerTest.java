package com.myproject.booking.infrastructure.controller;

import com.myproject.booking.application.repository.stub.ResourceRepositoryStub;
import com.myproject.booking.application.service.BookingService;
import com.myproject.booking.domain.Booking;
import com.myproject.booking.domain.BookingStatus;
import com.myproject.booking.domain.resource.Car;
import com.myproject.booking.domain.resource.Resource;
import com.myproject.booking.domain.valueObject.Periode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookingController.class)
public class BookingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private BookingService bookingService;

    @Test
    void shouldRetrieveBooking() throws Exception {
        Resource resource = ResourceRepositoryStub.getResources().get(0);

        Booking booking = new Booking();
        booking.setId(1);
        booking.setResource(resource);
        booking.setTotalPrice(500.0);
        booking.setStatus(BookingStatus.RESERVED);
        booking.setPeriode(new Periode(LocalDateTime.now(), LocalDateTime.now().plusDays(2)));
        when(bookingService.findById(1)).thenReturn(booking);

        mockMvc.perform(get("/booking/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idBooking").value(1))
                .andExpect(jsonPath("$.typeResource").value("car"));
    }
}
