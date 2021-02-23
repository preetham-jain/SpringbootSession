package com.example.SpringSession.dto;

import java.util.List;

public class ProductResponseDTO {

    private List<ProductDTO> products;
    private List<ProductDTO> locationBasedProducts;

    public List<ProductDTO> getLocationBasedProducts() {
        return locationBasedProducts;
    }

    public void setLocationBasedProducts(List<ProductDTO> locationBasedProducts) {
        this.locationBasedProducts = locationBasedProducts;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }
}

