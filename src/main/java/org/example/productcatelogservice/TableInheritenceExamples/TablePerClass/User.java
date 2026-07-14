package org.example.productcatelogservice.TableInheritenceExamples.TablePerClass;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity(name = "tpc_user")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id // To add id as primary key
    private  Long id;
    private String name;
}
