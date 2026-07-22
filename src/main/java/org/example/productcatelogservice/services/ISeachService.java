package org.example.productcatelogservice.services;

import java.util.List;

public interface ISeachService {


    List<String> searchProduct(String query, Integer pageSize, Integer pageNumber);
}
