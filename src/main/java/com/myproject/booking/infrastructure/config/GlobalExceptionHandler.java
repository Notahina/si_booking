package com.myproject.booking.infrastructure.config;

import com.myproject.booking.application.dto.ErrorResponseDTO;
import com.myproject.booking.application.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<?> businesExceptionHandler(BusinessException businessException){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(businessException.getMessage());
        errorResponseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponseDTO.setError("Traitement des donnés");
        return ResponseEntity.badRequest().body(errorResponseDTO);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleException(RuntimeException runtimeException){
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
        errorResponseDTO.setMessage(runtimeException.getMessage());
        errorResponseDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponseDTO.setError("Traitement des donnés");
        return new ResponseEntity<>(errorResponseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
