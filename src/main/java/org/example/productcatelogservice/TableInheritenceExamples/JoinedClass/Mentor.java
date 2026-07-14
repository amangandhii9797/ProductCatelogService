package org.example.productcatelogservice.TableInheritenceExamples.JoinedClass;


import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

@Entity(name = "jc_mentor")
@PrimaryKeyJoinColumn(name="user_id_")   // if we don't use this , the foreign key name would be Id, with this the FK name
// is changed from id to user_id
public class Mentor extends User {
    private  Double rating;
}
