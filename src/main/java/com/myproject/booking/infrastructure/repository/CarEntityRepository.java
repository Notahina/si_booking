package com.myproject.booking.infrastructure.repository;

import com.myproject.booking.infrastructure.entity.CarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarEntityRepository extends JpaRepository<CarEntity,Integer> {

}
