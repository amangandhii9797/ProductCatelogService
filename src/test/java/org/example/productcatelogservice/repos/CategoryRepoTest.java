package org.example.productcatelogservice.repos;

import jakarta.transaction.Transactional;
import org.example.productcatelogservice.models.Category;
import org.example.productcatelogservice.models.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CategoryRepoTest {

    @Autowired
    private CategoryRepo categoryRepo;

    @Test
    @Transactional
    public void testFetchTypes() {
        Optional<Category> categoryOptional = categoryRepo.findById(10L);
        Category category = categoryOptional.get();
        for(Product product : category.getProducts()) {
            System.out.println(product.getName());
        }
    }


    // N+1 problem

    /*
    * Solution to N+1 problem is :
    * 
    * 2. Use @Fetch(FetchMode.SUBSELECT) in the @OneToMany annotation in Category class.
    * 3. Use @BatchSize(size=2) in the @OneToMany annotation in Category class.
    *
    * */

    @Test
    @Transactional
    public void testNPlusOneProblem() {
        List<Category> categoryList = categoryRepo.findAll();
        for(Category category : categoryList) {
            System.out.println(category.getName());
            for(Product product : category.getProducts()) {
                System.out.println(product.getName());
            }
        }
    }

}