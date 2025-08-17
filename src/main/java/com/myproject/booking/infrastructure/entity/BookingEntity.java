package com.myproject.booking.infrastructure.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "booking")
public class BookingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookingId;

}
