package org.example.productcatelogservice.services;

import org.example.productcatelogservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ISearchService {


    Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber);
}
