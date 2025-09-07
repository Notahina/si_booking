package com.myproject.booking.application.repository;

import com.myproject.booking.domain.resource.Resource;

public interface IResourceRepository {
    Resource findById(String id);

    Resource save(Resource resource);
}
