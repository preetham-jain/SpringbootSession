package com.example.SpringSession.dto;

import java.util.List;

public class ProductResponseDTO {

    private List<ProductDTO> products;

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}

