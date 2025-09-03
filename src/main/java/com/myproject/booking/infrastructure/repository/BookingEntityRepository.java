package com.myproject.booking.infrastructure.repository;

import com.myproject.booking.infrastructure.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Set;

public interface BookingEntityRepository extends JpaRepository<BookingEntity,Integer> {

    @Query("select b from BookingEntity b where b.resourceId= :idResource and b.startDate < :endDate and b.endDate > :startDate")
    Set<BookingEntity> findOverlappingBookings(@Param("idResource") Integer idResource,
                                               @Param("startDate") LocalDateTime dateStart,
                                               @Param("endDate") LocalDateTime dateEnd);

}
