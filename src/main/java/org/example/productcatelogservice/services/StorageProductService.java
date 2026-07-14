package org.example.productcatelogservice.services;


import org.example.productcatelogservice.models.Product;
import org.example.productcatelogservice.repos.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Service("sps")
@Primary
public class StorageProductService implements IProductService {

    @Autowired
    private ProductRepo productRepo;

    @Override
    public Product getProductById(Long id) {
        Optional<Product> productOptional = productRepo.findById(id);
        if(productOptional.isEmpty()) {
            return null;
        }
        return productOptional.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product createProduct(Product product) {
        Assert.notNull(product.getId(), "Please pass id in request.");
        Optional<Product> optionalProduct =  productRepo.findById(product.getId());
        if (optionalProduct.isPresent()) {
            throw new RuntimeException("Product already exist with id"+product.getId());
        }

        return productRepo.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product input) {
        Optional<Product> optionalProduct =  productRepo.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new RuntimeException("Product with id"+id+" not available");
        }

        input.setId(id);
        return productRepo.save(input);
    }

    @Override
    public String deleteProduct(Long id){
        productRepo.deleteById(id);
        return "Product with id "+id+" deleted successfully";

    }
}