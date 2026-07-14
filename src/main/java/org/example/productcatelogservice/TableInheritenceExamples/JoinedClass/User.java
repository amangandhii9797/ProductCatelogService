package org.example.productcatelogservice.TableInheritenceExamples.JoinedClass;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "jc_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id // To add id as primary key
    private  Long id;
    private String name;
}
