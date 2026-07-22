package org.example.productcatelogservice.error;


import java.time.LocalDateTime;

public class ErrorResponse {
    private String message;
    private int statusCode;
    private LocalDateTime timestamp;

    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }

    // getters (or use Lombok @Data)
    public String getMessage() { return message; }
    public int getStatusCode() { return statusCode; }
    public LocalDateTime getTimestamp() { return timestamp; }
}