package org.example.productcatelogservice.error.customErrors;


public class InvalidProductDataException extends RuntimeException {
    public InvalidProductDataException(String message) {
        super(message);
    }
}