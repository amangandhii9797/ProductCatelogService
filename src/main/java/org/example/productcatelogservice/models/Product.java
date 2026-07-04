package org.example.productcatelogservice.models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product  extends  BaseModel{

    private String name;
    private String description;
    private Double price;
    private  String imageUrl;
    @ManyToOne(cascade = CascadeType.ALL)
    private  Category category;
    private  Boolean isPrimeSalesSpecific;
}
