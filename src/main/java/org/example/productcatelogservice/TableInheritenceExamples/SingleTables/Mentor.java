package org.example.productcatelogservice.TableInheritenceExamples.SingleTables;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "st_mentor")
@DiscriminatorValue(value = "MENTOR")
public class Mentor extends User {
    private  Double rating;
}
