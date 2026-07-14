package org.example.productcatelogservice.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatelogservice.Controllers.ProductController;
import org.example.productcatelogservice.dtos.ProductDto;
import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.productcatelogservice.dtos.ProductDto;
import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
public class ProductControllerMVCTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void TestGetProductByIdAPI_WithPositiveId_ReturnsEmptyResponseWith404() throws Exception {
        mockMvc.perform(get("/products/2"))     //Act
                .andExpect(status().isNotFound());    //Assert
    }

    @Test
    public void TestGetProductByIdAPI_WithPositiveId_ReturnsResponseSuccessfully() throws Exception {
        //Arrange
        Long id = 2L;
        Product product = new Product();
        product.setId(id);
        product.setName("Iphone");
        when(productService.getProductById(id)).thenReturn(product);

        ProductDto productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setName("Iphone");
        String expectedResponse = objectMapper.writeValueAsString(productDto);


        //Act and Assert
        mockMvc.perform(get("/products/2"))     //Act
                .andExpect(status().isOk())    //Assert
                .andExpect(content().string(expectedResponse));  //assertEquals(expectedResponse, received response converted into string)
    }
}


//{
//    "id" : 2, "name" : "Iphone"
//}