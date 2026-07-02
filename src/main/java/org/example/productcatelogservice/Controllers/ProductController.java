package org.example.productcatelogservice.Controllers;


import org.apache.coyote.Response;
import org.example.productcatelogservice.dtos.CategoryDto;
import org.example.productcatelogservice.dtos.ProductDto;
import org.example.productcatelogservice.models.Category;
import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

//I need only one instance of this controller class
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping()
    public List<ProductDto> getAllProducts() {
        // Implementation to retrieve all products
        ProductDto productDto = new ProductDto();
        productDto.setId(2L);
        productDto.setName("Product 2");
        List<ProductDto>  productList = new ArrayList<>();
        productList.add(productDto);
        return  productList;

     
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("id") Long productId) {
        if (productId <= 0) {
            //return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            throw new IllegalArgumentException("Please pass positive product Id");
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            //return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            throw new NullPointerException("Product not available");
        }
        ProductDto productDto = from(product);
        return new ResponseEntity<>(productDto,HttpStatus.OK);
    }


    @PutMapping("{id}")
    public ResponseEntity<ProductDto> replaceProduct(@PathVariable Long id, @RequestBody ProductDto productDto){
        if(id <=  0){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        // convert this product Dto to product before sending it to service layer
        Product inputProduct = from(productDto);
        // service layer call
        Product outputProduct = productService.replaceProduct(id, inputProduct);

        if(outputProduct == null){
            new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(from(outputProduct), HttpStatus.OK);

    }


    @DeleteMapping("{id}")      // To Do

    @PostMapping()
    public  ProductDto createProduct(@RequestBody ProductDto productDto) {         // To Do
        return productDto;

        
    }
    private Product from(ProductDto productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());
            product.setCategory(category);
        }
        return product;
    }

    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            productDto.setCategory(categoryDto);
        }

        return productDto;
    }
}
