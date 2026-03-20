package com.service.exchange.controller.handler;

import java.time.Instant;

public class ErrorResponse {

    private String message;
    private String errorCode;
    private int status;
    private String timestamp;

    public ErrorResponse(String message, String errorCode, int status) {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
        this.timestamp = Instant.now().toString();
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getErrorCode() { return errorCode; }
    public void setErrorCode(String errorCode) { this.errorCode = errorCode; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }

    public String getTimestamp() { return timestamp; }
    public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
}
