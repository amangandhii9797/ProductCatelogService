package org.example.productcatelogservice.error.customErrors;

public class ProductServiceException extends RuntimeException {
    public ProductServiceException(String message) {
        super(message);
    }
}