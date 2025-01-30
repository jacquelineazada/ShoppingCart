package com.example.shoppingcart.controller;


import com.example.shoppingcart.model.Product;
import com.example.shoppingcart.repository.ProductRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Get all products
    @GetMapping( "/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

}
