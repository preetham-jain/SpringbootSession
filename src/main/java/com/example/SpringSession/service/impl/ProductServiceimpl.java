package com.example.SpringSession.service.impl;

import com.example.SpringSession.dto.ProductDTO;
import com.example.SpringSession.dto.ProductRequestDTO;
import com.example.SpringSession.dto.ProductResponseDTO;
import com.example.SpringSession.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ProductServiceimpl implements ProductService {
    @Override
    public ProductResponseDTO getProducts(ProductRequestDTO request, String SearchTerm) {
        ProductResponseDTO responseDTO = new ProductResponseDTO();
        ProductDTO product = new ProductDTO();
        product.setDescription("Used to whack people!");
        product.setInStock(true);
        product.setSalesPrice(12000);
        product.setTitle("Guitar");
        responseDTO.setProducts(Arrays.asList(product));
        return responseDTO;
    }
}
