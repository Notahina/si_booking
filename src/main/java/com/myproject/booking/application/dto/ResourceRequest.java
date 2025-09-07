package com.myproject.booking.application.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "resourceType"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CarRequest.class ,name = "car"),
})
public abstract class ResourceRequest {
    private String name;
    private String description;
    private String availability = "true";
    private String resourceType;
}
