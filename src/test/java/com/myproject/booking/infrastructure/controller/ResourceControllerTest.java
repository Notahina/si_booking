package com.myproject.booking.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myproject.booking.application.dto.CarRequest;
import com.myproject.booking.domain.resource.FuelType;
import com.myproject.booking.domain.resource.ResourceType;
import com.myproject.booking.domain.resource.Transmission;
import com.myproject.booking.infrastructure.entity.CarEntity;
import com.myproject.booking.infrastructure.entity.ResourcesEntity;
import com.myproject.booking.infrastructure.repository.CarEntityRepository;
import com.myproject.booking.infrastructure.repository.ResourcesEntityRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ResourceControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper ;

    @MockitoBean
    private ResourcesEntityRepository resourcesEntityRepository;

    @MockitoBean
    private CarEntityRepository carEntityRepository;

    @Test
    void createCarResource() throws Exception {
        CarRequest carRequest = new CarRequest();
        carRequest.setName("test");
        carRequest.setColor("black");
        carRequest.setMarque("TEST");
        carRequest.setModel("Toyota");
        carRequest.setMatricule("12345");
        carRequest.setTransmission("AUTOMATIQUE");
        carRequest.setYear("2020");
        carRequest.setFuelType("essence");
        carRequest.setDailyPrice(60.0);

    String jsonRequest = objectMapper.writeValueAsString(carRequest);

    when(resourcesEntityRepository.save(any())).then(invocation -> {
        ResourcesEntity resourcesEntity = invocation.getArgument(0,ResourcesEntity.class);
        resourcesEntity.setId(123);
        return resourcesEntity;
    });

    when(carEntityRepository.save(any())).then(invocation -> {
        CarEntity carEntity = invocation.getArgument(0,CarEntity.class);
        carEntity.setIdCar(123);
        return carEntity;
    });

    mockMvc.perform(post("/resource/")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(jsonRequest))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.name").value(carRequest.getName()));
    }


    @Test
    void retrieveResource() throws Exception{
        ResourcesEntity resourcesEntity = new ResourcesEntity();
        resourcesEntity.setId(1);
        resourcesEntity.setName("test");
        resourcesEntity.setResourceType(ResourceType.CAR);

        CarEntity carEntity = new CarEntity();
        carEntity.setIdCar(1);
        carEntity.setResources(resourcesEntity);
        carEntity.setAnnee("2020");
        carEntity.setNbrePlace(5);
        carEntity.setModel("Toyota");
        carEntity.setColor("Noir");
        carEntity.setMarque("TEST");
        carEntity.setFuelType(FuelType.DIESEL);
        carEntity.setDailyPrice(60.0);
        carEntity.setTransmission(Transmission.AUTOMATIQUE);

        when(resourcesEntityRepository.findById(1)).thenReturn(Optional.of(resourcesEntity));
        when(carEntityRepository.findById(1)).thenReturn(Optional.of(carEntity));
        mockMvc.perform(get("/resource/1"))
                .andExpect(status().isOk());
    }
}
