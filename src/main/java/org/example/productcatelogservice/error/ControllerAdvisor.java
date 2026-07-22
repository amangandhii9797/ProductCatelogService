package org.example.productcatelogservice.error;

import org.example.productcatelogservice.error.customErrors.InvalidProductDataException;
import org.example.productcatelogservice.error.customErrors.ProductNotFoundException;
import org.example.productcatelogservice.error.customErrors.ProductServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor {

    // Your custom, meaningful exceptions
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFound(ProductNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidProductDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProductData(InvalidProductDataException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Generic/built-in exceptions
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    // Catch-all fallback for anything unhandled — prevents raw stack traces leaking to clients
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                "Something went wrong: " + ex.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }


   

    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<ErrorResponse> handleProductServiceException(ProductServiceException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_GATEWAY.value());
        return new ResponseEntity<>(error, HttpStatus.BAD_GATEWAY); // 502 makes sense since the upstream (FakeStore) failed
    }

    /*

          Handling multiple exceptions in one method

If several exceptions should return the same response shape, list them together:

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
public ResponseEntity<ErrorResponse> handleBadRequests(RuntimeException ex) {
    ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
}*/
}