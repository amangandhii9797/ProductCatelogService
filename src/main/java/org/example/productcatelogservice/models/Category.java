package org.example.productcatelogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.example.productcatelogservice.models.BaseModel;
import org.example.productcatelogservice.models.Product;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel {
    private String name;
    private String description;
    @OneToMany(mappedBy = "category")
   /* @OneToMany(mappedBy = "category" , fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "category" , fetch = FetchType.LAZY)*/
    //@Fetch(FetchMode.SELECT)
    //@BatchSize(size=2)
    private List<Product> products;
}

//Fetch Type decide when entity will be fetched by DB
//Fetch Mode will decide how to fetch entity detail from DB


//6 select queries from product
//        batch of 3 queries
//        in 1 batch - 3 queries will run parallely
//
//        and batch will run sequentially
//
//
//1 (category)+ 4(product)
//size = 2
//1 + 2 = 3 batches