package com.myproject.booking.application.repository.stub;

import com.myproject.booking.application.ddd.Stub;
import com.myproject.booking.application.repository.IResourceRepository;
import com.myproject.booking.domain.resource.*;

import java.util.ArrayList;
import java.util.List;

@Stub
public class ResourceRepositoryStub implements IResourceRepository {
    public ResourceRepositoryStub() {

    }

    public static List<Resource> getResources() {
        List<Resource> resources = new ArrayList<>();
        Car car1 = Car.builder().idResource("CAR-01").name("Mazda 01").available(true)
                .transmission(Transmission.AUTOMATIQUE)
                .dailyRate(50)
                .model("peu")
                .year("2000")
                .marque("V12")
                .fuelType(FuelType.DIESEL)
                .numberOfSeats(5).build();
        Car car2 = Car.builder().idResource("CAR-02").name("Mazda 02").available(true).build();
        Car car3 = Car.builder().idResource("CAR-03").name("Mazda 03").build();

        Room room = Room.builder().idResource("R-01").name("Hote 01").available(true).build();
        Room room1 = Room.builder().idResource("R-02").name("Hote 02").available(true).build();
        Room room2 = Room.builder().idResource("R-03").name("Hote 03").build();

        resources.add(car1);
        resources.add(car2);
        resources.add(car3);
        resources.add(room);
        resources.add(room1);
        resources.add(room2);
        return resources;
    }

    @Override
    public Resource findById(String id) {
        return getResources().stream().filter(r -> r.getIdResource().equalsIgnoreCase(id)).findFirst().orElse(null);
    }
}
