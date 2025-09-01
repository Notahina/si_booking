package com.myproject.booking.application.dto;

import lombok.experimental.SuperBuilder;

@SuperBuilder
public  class ResourceDetailResponse {
    private String id;
    private String name;
    private String description;
}