package com.service.exchange.controller.handler;

import com.service.exchange.service.exception.CurrencyExchangeException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CurrencyExchangeException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(CurrencyExchangeException ex, HttpServletRequest request) {

        ErrorResponse error = new ErrorResponse(
                ex.getMessage(),
                "",
                HttpStatus.BAD_REQUEST.value()
        );

        log.error("Validation failed for request {}: {}",
                request.getRequestURI(), ex.getMessage(), ex);

        return new ResponseEntity<>(error,  HttpStatus.BAD_REQUEST);
    }

}
