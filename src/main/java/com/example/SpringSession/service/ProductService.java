package com.example.SpringSession.service;

import com.example.SpringSession.dto.ProductRequestDTO;
import com.example.SpringSession.dto.ProductResponseDTO;

public interface ProductService {
    ProductResponseDTO getProducts(ProductRequestDTO request, String SearchTerm);
}
