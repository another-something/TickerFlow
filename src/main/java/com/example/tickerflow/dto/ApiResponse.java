package com.example.tickerflow.dto;

public class ApiResponse {

    private boolean valid;
    private String message;

    public ApiResponse(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }

}
