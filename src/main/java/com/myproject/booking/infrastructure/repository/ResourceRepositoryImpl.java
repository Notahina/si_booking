package com.myproject.booking.infrastructure.repository;

import com.myproject.booking.application.repository.IResourceRepository;
import com.myproject.booking.domain.resource.Car;
import com.myproject.booking.domain.resource.Resource;
import com.myproject.booking.domain.resource.ResourceType;
import com.myproject.booking.infrastructure.entity.CarEntity;
import com.myproject.booking.infrastructure.entity.ResourcesEntity;
import com.myproject.booking.infrastructure.mapper.EntityMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ResourceRepositoryImpl implements IResourceRepository {

    private  final ResourcesEntityRepository resourcesEntityRepository ;
    private final CarEntityRepository carEntityRepository;

    public ResourceRepositoryImpl(ResourcesEntityRepository resourcesEntityRepository, CarEntityRepository carEntityRepository) {
        this.resourcesEntityRepository = resourcesEntityRepository;
        this.carEntityRepository = carEntityRepository;
    }


    @Override
    public Resource findById(String id) {
        ResourcesEntity resourcesEntity = resourcesEntityRepository.findById(Integer.valueOf(id)).orElse(null);
        if(resourcesEntity == null)
            return null;
        switch (resourcesEntity.getResourceType()){
            case CAR -> {
                CarEntity carEntity = carEntityRepository.findById(Integer.valueOf(id)).orElse(null);
                if (carEntity == null)
                    return null;
                return EntityMapper.mapCar(carEntity);
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public Resource save(Resource resource) {
        if (resource == null) {
            return null;
        }
        if (resource instanceof Car car){
            return saveNewCar(car);
        }
        return null;
    }

    @Transactional
    private Car saveNewCar(Car car) {
        ResourcesEntity resourcesEntity = new ResourcesEntity();
        resourcesEntity.setResourceType(ResourceType.CAR);
        resourcesEntity.setDescription(car.getDescription());
        resourcesEntity.setName(car.getName());
        resourcesEntity.setDescription(car.getDescription());

        ResourcesEntity savedResource = resourcesEntityRepository.save(resourcesEntity);

       CarEntity carEntity = EntityMapper.carToEntity(car);
       carEntity.setResources(savedResource);
       CarEntity  carEntitySaved = carEntityRepository.save(carEntity);

       return EntityMapper.mapCar(carEntitySaved);
    }
}
