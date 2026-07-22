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

import static org.junit.jupiter.api.Assertions.*;
        import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private IProductService productService;   // I will mock the product service object using MockBean

    @Test
    public void TestGetProductById_WithPositiveProductId_ReturnsProductSuccessfully()   // name of the test should be very clear 
    {
        //Arrange
        Long id = 2L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone");
        when(productService.getProductById(id)).thenReturn(product);   // ->   defining the behaviour of the mocked productService object, when getProductById is called with id=2L,
        // it will return the product object created above

        //Act
        ResponseEntity<ProductDto> productDtoResponseEntity =  productController.getProductById(id);    // -> this will call the actual controller method
        // and when it goes inside, I have a mocked productService object for which behaviour have been defined


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

    }

    @Test
    public void testDeleteProductById_WithPositveProductId_ReturnsDeleteSuccessfully() {
        // Arrange
        Long productId = 2L;
        String expectedResponse = "Delete Successfully";
        when(productService.deleteProduct(productId)).thenReturn(expectedResponse);

        // Act
        ResponseEntity<String> responseEntity = productController.deleteProduct(productId);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(expectedResponse, responseEntity.getBody());
    }


    @Test
    public void testDeleteProductById_WithNegativeProductId_ReturnsBadRequest() {
        // Arrange
        Long productId = -2L;

        // Act
        ResponseEntity<String> responseEntity = productController.deleteProduct(productId);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(400, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());
    }



    @Test
    public void testReplaceProduct_WithPositiveProductId_ReturnsUpdatedProduct() {
        // Arrange
        Long productId = 2L;
        ProductDto inputProductDto = new ProductDto();
        inputProductDto.setId(productId);
        inputProductDto.setName("Updated Product");

        Product updatedProduct = new Product();
        updatedProduct.setId(productId);
        updatedProduct.setName("Updated Product");

        when(productService.replaceProduct(productId, productController.from(inputProductDto)))
                .thenReturn(updatedProduct);


        // Act
        ResponseEntity<ProductDto> responseEntity = productController.replaceProduct(productId, inputProductDto);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertNotNull(responseEntity.getBody());
        assertEquals(productId, responseEntity.getBody().getId());
        assertEquals("Updated Product", responseEntity.getBody().getName());
    }


    @Test
    public void testReplaceProduct_WithNegativeProductId_ReturnsBadRequest() {
        // Arrange
        Long productId = -2L;
        ProductDto inputProductDto = new ProductDto();
        inputProductDto.setId(productId);
        inputProductDto.setName("Updated Product");

        // Act
        ResponseEntity<ProductDto> responseEntity = productController.replaceProduct(productId, inputProductDto);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(400, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());
    }

    @Test
    public void testReplaceProduct_WithNullProductId_ReturnsBadRequest() {
        // Arrange
        Long productId = null;
        ProductDto inputProductDto = new ProductDto();
        inputProductDto.setId(productId);
        inputProductDto.setName("Updated Product");

        // Act
        ResponseEntity<ProductDto> responseEntity = productController.replaceProduct(productId, inputProductDto);

        // Assert
        assertNotNull(responseEntity);
        assertEquals(400, responseEntity.getStatusCodeValue());
        assertNull(responseEntity.getBody());
    }

}