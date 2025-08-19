package com.myproject.booking.infrastructure.repository;

import com.myproject.booking.application.repository.IResourceRepository;
import com.myproject.booking.domain.resource.Resource;
import com.myproject.booking.infrastructure.entity.CarEntity;
import com.myproject.booking.infrastructure.entity.ResourcesEntity;
import com.myproject.booking.infrastructure.mapper.EntityMapper;
import org.springframework.stereotype.Component;

@Component
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
}
