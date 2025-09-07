package com.myproject.booking.application.utils.mapper;

import com.myproject.booking.application.dto.CarDetailResponse;
import com.myproject.booking.application.dto.CarRequest;
import com.myproject.booking.application.dto.ResourceDetailResponse;
import com.myproject.booking.application.exception.BusinessException;
import com.myproject.booking.domain.resource.Car;
import com.myproject.booking.domain.resource.FuelType;
import com.myproject.booking.domain.resource.Resource;
import com.myproject.booking.domain.resource.Transmission;

public class ResourceMapper {

    public static Car buildCar(CarRequest carRequest) throws BusinessException {
        Car car = Car.builder()
                .name(carRequest.getName())
                .description(carRequest.getDescription())
                .available(Boolean.parseBoolean((carRequest.getAvailability()).toLowerCase()))
                .year(carRequest.getYear())
                .model(carRequest.getModel())
                .marque(carRequest.getMarque())
                .dailyRate(carRequest.getDailyPrice())
                .numberOfSeats(carRequest.getNumberOfSeats())
                .color(carRequest.getColor())
                .licensePlate(carRequest.getMatricule())
                .build();
        try{
            FuelType fuelType = FuelType.valueOf((carRequest.getFuelType()).toUpperCase());
            car.setFuelType(fuelType);

            Transmission transmission = Transmission.valueOf((carRequest.getTransmission()).toUpperCase());
            car.setTransmission(transmission);
        }catch (IllegalArgumentException e){
            throw new BusinessException("Invalid fuel type or transmission");
        }
        return car;
    }

    public static ResourceDetailResponse toResponse(Resource resource) {
        if (resource instanceof Car car){
            CarDetailResponse carResponse = new CarDetailResponse();
            carResponse.setName(car.getName());
            carResponse.setDescription(car.getDescription());
            carResponse.setBrand(car.getMarque());
            carResponse.setColor(car.getColor());
            carResponse.setFuelType(car.getFuelType().name());
            carResponse.setYear(car.getYear());
            carResponse.setTransmission(car.getTransmission().name());
            carResponse.setId(car.getIdResource());
            carResponse.setModel(car.getModel());
            carResponse.setDailyPrice(car.getDailyRate());
            return   carResponse;
        }
        return null;
    }
}
