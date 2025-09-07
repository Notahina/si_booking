package com.myproject.booking.application.service;

import com.myproject.booking.application.ddd.DomainService;
import com.myproject.booking.application.dto.CarRequest;
import com.myproject.booking.application.dto.ResourceRequest;
import com.myproject.booking.application.exception.BusinessException;
import com.myproject.booking.application.repository.IResourceRepository;
import com.myproject.booking.application.utils.mapper.ResourceMapper;
import com.myproject.booking.domain.resource.Car;
import com.myproject.booking.domain.resource.Resource;
import com.myproject.booking.domain.resource.ResourceType;
import org.apache.commons.lang3.StringUtils;

@DomainService
public class ResourceService implements IResourceService{
    private final IResourceRepository resourceRepository;

    public ResourceService(IResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    @Override
    public Resource addResource(ResourceRequest resourceRequest) throws BusinessException {
        if (resourceRequest.getResourceType().equalsIgnoreCase(ResourceType.CAR.name())){
           Car car = ResourceMapper.buildCar((CarRequest) resourceRequest);
           carValidation(car);

           return resourceRepository.save(car);
        }
        return null;
    }

    @Override
    public Resource findById(Integer id) throws BusinessException {
        Resource resource = resourceRepository.findById((id).toString());
        if (resource == null){
            throw new BusinessException("Resource not found");
        }
        return resource;
    }

    private void carValidation(Car car) throws BusinessException {
        if (Integer.parseInt(car.getYear()) < 2000) {
            throw new BusinessException("Vehicule doit avoir une année supérieure à 2000");
        }
        if (StringUtils.isBlank(car.getLicensePlate()) || car.getLicensePlate().length() < 3) {
            throw new BusinessException("Matriculation invalide");
        }
    }
}
