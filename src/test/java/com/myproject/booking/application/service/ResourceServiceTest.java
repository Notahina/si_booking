package com.myproject.booking.application.service;

import com.myproject.booking.application.dto.CarRequest;
import com.myproject.booking.application.exception.BusinessException;
import com.myproject.booking.application.repository.IResourceRepository;
import com.myproject.booking.application.repository.stub.ResourceRepositoryStub;
import com.myproject.booking.domain.resource.Car;
import com.myproject.booking.domain.resource.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResourceServiceTest {
    private IResourceService resourceService;

    @BeforeEach
    void setUp(){
        IResourceRepository resourceRepository = new ResourceRepositoryStub();
        resourceService = new ResourceService(resourceRepository);
    }

    @Test
    void testCreateResourceCar() throws BusinessException {
        CarRequest carRequest = new CarRequest();
        carRequest.setName("test");
        carRequest.setColor("black");
        carRequest.setMarque("TEST");
        carRequest.setModel("Toyota");
        carRequest.setMatricule("12345");
        carRequest.setTransmission("AUTOMATIQUE");
        carRequest.setAvailability("true");
        carRequest.setYear("2020");
        carRequest.setFuelType("ESSENCE");
        carRequest.setDailyPrice(60.0);

        Resource carResource =  resourceService.addResource(carRequest);

        Assertions.assertNotNull(carResource);
        Assertions.assertInstanceOf(Car.class, carResource);
        Assertions.assertTrue(carResource.getIdResource().contains("CAR"));
    }
}
