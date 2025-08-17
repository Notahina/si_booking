package com.myproject.booking.domain.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@SuperBuilder
@Data
public  abstract class Resource {
    private String idResource;
    private String name;
    private String description;
    private boolean available = true;

    public abstract double calculatePrice(LocalDateTime dateStart, LocalDateTime dateEnd);

    public void markAsAvailable() {
        this.available = true;
    }
    public void markAsBooked() {
        this.available = false;
    }

    public String errorMessageAvailable() {
        return "";
    }


    public String errorMessageCanceled() {
        return "";
    }

    public String getTypeResource() {
        return this.getClass().getSimpleName();
    }


}
