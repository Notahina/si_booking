package com.myproject.booking.domain.resource;

import lombok.Getter;

public enum Transmission {
    AUTOMATIQUE("automatique"),
    MANUELLE("manuelle"),
    SEMI_AUTOMATIQUE("semi_automatique"),
    CTV("ctv");

    @Getter
    private String value;
    Transmission(String value) {
        this.value = value;
    }
}
