package com.example.SpringSession.controller;

import com.example.SpringSession.dto.ProductRequestDTO;
import com.example.SpringSession.dto.ProductResponseDTO;
import com.example.SpringSession.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class ProductController {

    public ProductController(ProductService productService) {
        this.productService = productService;
        System.out.println("Inside product controller constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("Inside product controller postconstruct");
    }

    @Autowired
    private ProductService productService;

    @PostMapping(path = "/search/{SearchTerm}")
    public ProductResponseDTO Search(@RequestBody ProductRequestDTO request, @PathVariable String SearchTerm) {
        return productService.getProducts(request, SearchTerm);
    }
}
