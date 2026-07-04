package org.example.productcatelogservice.models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

        // THIS IS USING LAMBOK SPRING BOOT DEPENDENCY
@Getter
@Setter
@MappedSuperclass
public abstract class BaseModel {    //As we will never create class of BaseModel so we are making it abstract class
    @Id
    private  Long id;
    private   Date createdDate;
    private Date lastUpdatedAt;
    private State state;




}
