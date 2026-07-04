package org.example.productcatelogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;



@Getter
@Setter
@Entity
public class Category extends  BaseModel{
    private  String name;
    private  String  description;
    @OneToMany(mappedBy = "category") // tells that category field in product class is the owner of the relationship
    private List<Product> products;

}
