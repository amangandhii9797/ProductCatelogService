package org.example.productcatelogservice.repos;

import org.example.productcatelogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo  extends JpaRepository<Category, Long> {
}
