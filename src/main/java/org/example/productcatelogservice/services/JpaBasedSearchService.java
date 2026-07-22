package org.example.productcatelogservice.services;


import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaBasedSearchService  implements  ISeachService{


    @Override
    public List<String> searchProduct(String query, Integer pageSize, Integer pageNumber) {
        return List.of();
    }
}
