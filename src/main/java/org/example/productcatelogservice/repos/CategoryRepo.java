package org.example.productcatelogservice.repos;

import org.example.productcatelogservice.models.Category;
import org.example.productcatelogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepo  extends JpaRepository<Category, Long> {


    Optional<Category> findById(Long id);
}
