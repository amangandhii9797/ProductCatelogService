package org.example.productcatelogservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequestDto {

    private String query;
    private Integer pagesize;
    private  Integer pageNumber;
}
