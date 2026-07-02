package org.example.productcatelogservice.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.productcatelogservice.models.Category;


@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)  // Annotation from jackson, to
// make sure the null values are not included in the response
public class ProductDto {

    private  Long id;
    private String name;
    private String description;
    private Double price;
    private  String imageUrl;
    private CategoryDto category;
}
