package com.myproject.booking.infrastructure.mapper;

import com.myproject.booking.domain.resource.Car;
import com.myproject.booking.infrastructure.entity.CarEntity;

public class EntityMapper {
    public static Car mapCar(CarEntity carEntity) {
        if (carEntity == null)
            return null;
        return Car.builder()
                .idResource(carEntity.getResources().getId().toString())
                .name(carEntity.getResources().getName())
                .description(carEntity.getResources().getDescription())
                .available(true)
                .color(carEntity.getColor())
                .year(carEntity.getAnnee())
                .model(carEntity.getModel())
                .fuelType(carEntity.getFuelType())
                .licensePlate(carEntity.getLicencePlate())
                .marque(carEntity.getMarque())
                .transmission(carEntity.getTransmission())
                .dailyRate(carEntity.getDailyPrice())
                .build();
    }


}
