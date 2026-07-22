package org.example.productcatelogservice.Controllers;


import org.example.productcatelogservice.dtos.ProductDto;
import org.example.productcatelogservice.dtos.SearchRequestDto;
import org.example.productcatelogservice.services.ISeachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/search")
public class SearchController {



    @Autowired
    private ISeachService searchService;

    public ProductDto search(SearchRequestDto searchRequestDto) {
        // Implementation for search functionality

      //  searchService
        return null;
    }
}
