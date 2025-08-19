package com.myproject.booking.infrastructure.repository;

import com.myproject.booking.infrastructure.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingEntityRepository extends JpaRepository<BookingEntity,Integer> {
}
