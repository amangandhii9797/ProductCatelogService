package org.example.productcatelogservice.services;


import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.repos.ProductRepo;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class JpaBasedSearchService implements ISearchService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Page<Product> searchProducts(String query, Integer pageSize, Integer pageNumber) {
        Sort sortByIdDesc = Sort.by("id").descending();
        Sort sortByPriceDesc = Sort.by("price").descending();
        Sort sort = sortByPriceDesc.and(sortByIdDesc);
        return productRepo.findByName(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}

//
//
//{
//    [ {"paramName" : "price","sortType" : "ASC"} ,
//        {"paramName" : "id","sortType" : "DESC"}  ]
//}
//
//
//        {
//        "query" : "laptop",
//        "pageSize" : 10,
//        "pageNumber" : 0,
//        "sortParam" : [
//        {
//        "paramName" : "price",
//        "sortType" : "ASC"
//        } ,
//        {
//        "paramName" : "id",
//        "sortType" : "DESC"
//        }
//        ]
//        }