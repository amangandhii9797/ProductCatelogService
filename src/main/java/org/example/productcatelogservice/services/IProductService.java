package org.example.productcatelogservice.services;

import org.example.productcatelogservice.ProductCatelogServiceApplication;
import org.example.productcatelogservice.models.Product;

import java.util.List;

public interface IProductService {

    Product getProductById(Long productId);

    List<Product> getAllProducts();

    Product createProduct(Product product);

    Product replaceProduct(Long id, Product product);

    String deleteProduct(Long id);

    Product getProductBasedOnUserRole(Long productId, Long userId);



    
    /*
    Steps to call any Rest API

    1. Test the endpoint
    2.
    *
    * */
}
