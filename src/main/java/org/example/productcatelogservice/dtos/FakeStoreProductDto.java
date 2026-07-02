package org.example.productcatelogservice.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class FakeStoreProductDto {

    private  String title;
    private Long Id;
    private String description;
    private String category;
    private Double price;
    private  String image; 
}
