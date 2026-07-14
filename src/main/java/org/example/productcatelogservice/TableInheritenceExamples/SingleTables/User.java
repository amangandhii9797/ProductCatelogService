package org.example.productcatelogservice.TableInheritenceExamples.SingleTables;


import jakarta.persistence.*;

@Entity(name = "st_user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="user_type", discriminatorType = DiscriminatorType.STRING)
public class User {
    @Id // To add id as primary key
    private  Long id;
    private String name;
}
