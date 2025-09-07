package com.myproject.booking.application.service;

import com.myproject.booking.application.dto.ResourceRequest;
import com.myproject.booking.application.exception.BusinessException;
import com.myproject.booking.domain.resource.Resource;

public interface IResourceService {
    Resource addResource(ResourceRequest resourceRequest) throws BusinessException;

    Resource findById(Integer id) throws BusinessException;
}
