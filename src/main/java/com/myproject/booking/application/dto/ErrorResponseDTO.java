package com.myproject.booking.application.dto;

import lombok.Data;

@Data
public class ErrorResponseDTO {
    private String message;
    private String error = "Bad Request";
    private int status;
}
