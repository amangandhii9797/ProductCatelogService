package org.example.productcatelogservice.repos;

import org.example.productcatelogservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo  extends JpaRepository<Product, Long> {
}
