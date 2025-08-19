package com.myproject.booking.infrastructure.repository;

import com.myproject.booking.infrastructure.entity.ResourcesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourcesEntityRepository extends JpaRepository<ResourcesEntity,Integer> {
}
