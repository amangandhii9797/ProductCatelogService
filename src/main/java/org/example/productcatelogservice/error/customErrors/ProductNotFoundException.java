package org.example.productcatelogservice.error.customErrors;


public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(String message) {
        super(message);
    }
}
