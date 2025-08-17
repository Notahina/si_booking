package com.myproject.booking.domain.resource;

import lombok.Getter;

public enum FuelType {
    ESSENCE("essence"),
    ELECTRIQUE("electrique/hybride"),
    DIESEL("diesel"),;

    @Getter
    public  String value;
    FuelType (String label){
        this.value = label;
    }
}
