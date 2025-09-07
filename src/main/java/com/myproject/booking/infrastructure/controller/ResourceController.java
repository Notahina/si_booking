package com.myproject.booking.infrastructure.controller;

import com.myproject.booking.application.dto.ResourceRequest;
import com.myproject.booking.application.service.IResourceService;
import com.myproject.booking.application.utils.mapper.ResourceMapper;
import com.myproject.booking.domain.resource.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResourceController {

    private final IResourceService resourceService;

    public ResourceController(IResourceService resourceService) {
        this.resourceService = resourceService;
    }

    @PostMapping("/resource/")
    public ResponseEntity<?> createResource(@RequestBody ResourceRequest resourceRequest) throws Exception {
        Resource resource = resourceService.addResource(resourceRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(ResourceMapper.toResponse(resource));
    }

    @GetMapping("/resource/{id}")
    public ResponseEntity<?> findResourceById(@PathVariable("id") Integer idResource) throws Exception {
        Resource resource = resourceService.findById(idResource);
        return ResponseEntity.status(HttpStatus.OK).body(ResourceMapper.toResponse(resource));
    }
}
