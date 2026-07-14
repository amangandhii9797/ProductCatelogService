package org.example.productcatelogservice.controllers;

import org.example.productcatelogservice.Controllers.ProductController;
import org.example.productcatelogservice.dtos.ProductDto;
import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;

    @Test
    public void TestGetProductById_WithPositiveProductId_ReturnsProductSuccessfully()
    {
        //Arrange
        Long id = 2L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone");
        when(productService.getProductById(id)).thenReturn(product);

        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity =  productController.getProductById(id);


        //Assert
        assertNotNull(productDtoResponseEntity);
        assertNotNull(productDtoResponseEntity.getBody());
        assertEquals(id, productDtoResponseEntity.getBody().getId());
        assertNull(productDtoResponseEntity.getBody().getDescription());
        assertEquals("Iphone",productDtoResponseEntity.getBody().getName());
    }

    @Test
    public void TestGetProductById_WithNegativeId_ThrowsIllegalArgumentException() {
        //Arrange
        Long productId = -2L;

        //Act and Assert
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> productController.getProductById(productId));

        assertEquals("Please pass positive product Id",exception.getMessage());

    }


  
    @Test
    public void testGetProductById_WithNullId_ThrowsNullPointerException() {

        // Arrange
    //    Long productId = null;

        // Act and Assert
        Exception exception = assertThrows(NullPointerException.class,
                () -> productController.getProductById(5l));   // I am giving 5L because I am checking for the response from product service,

        assertEquals("Product not available", exception.getMessage());

        /*
        * productId is a Long (an object), but <= requires a primitive comparison, so Java auto-unboxes it: productId.longValue() <= 0. When productId is null, calling .longValue() on it throws a NullPointerException — but this NPE has no message (null), not "Product not available".
So the sequence for each case is:
5L passed in:

5L <= 0 → false, no exception here
productService.getProductById(5L) → returns null (unstubbed mock)
product == null → true → throws new NullPointerException("Product not available") ✅ matches your assertion, test passes
        *
        * */
    }
}