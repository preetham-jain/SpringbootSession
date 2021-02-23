package com.example.SpringSession.service.impl;

import com.example.SpringSession.client.SearchClient;
import com.example.SpringSession.dto.ProductDTO;
import com.example.SpringSession.dto.ProductRequestDTO;
import com.example.SpringSession.dto.ProductResponseDTO;
import com.example.SpringSession.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceimpl implements ProductService {
    @Autowired
    private SearchClient searchClient;
    @Override
    public ProductResponseDTO getProducts(ProductRequestDTO request) {
        
        Map<String, Object> products = searchClient.getProducts(request.getSearchTerm());
        List<Map<String, Object>> resp = (List<Map<String, Object>>) ((Map)products.get("response")).get("docs");

        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Map<String, Object> productClientResponse :resp) {
            String title = (String) productClientResponse.get("name");
            boolean inStock = (int) productClientResponse.get("isInStock") == 1? true: false;
            String description = (String) productClientResponse.get("description");
            double salePrice = (double) productClientResponse.get("salePrice");

            ProductDTO productDTO = new ProductDTO();
            productDTO.setSalesPrice(salePrice);
            productDTO.setInStock(inStock);
            productDTO.setTitle(title);
            productDTO.setDescription(description);

            productDTOS.add(productDTO);
        }

        ProductResponseDTO responseDTO = new ProductResponseDTO();
        responseDTO.setProducts(productDTOS);

        String location = "stockLocation:" + request.getStockLocation();
        Map<String, Object> locationProducts = searchClient.getProducts(location);
        List<Map<String, Object>> locationResponse = (List<Map<String, Object>>) ((Map)locationProducts.get("response")).get("docs");

        List<ProductDTO> locationProductDTOS = new ArrayList<>();
        for (Map<String, Object> productClientResponse :locationResponse) {
            String title = (String) productClientResponse.get("name");
            boolean inStock = (int) productClientResponse.get("isInStock") == 1? true: false;
            String description = (String) productClientResponse.get("description");
            double salePrice = (double) productClientResponse.get("salePrice");

            ProductDTO productDTO = new ProductDTO();
            productDTO.setSalesPrice(salePrice);
            productDTO.setInStock(inStock);
            productDTO.setTitle(title);
            productDTO.setDescription(description);

            locationProductDTOS.add(productDTO);
        }

        responseDTO.setLocationBasedProducts(locationProductDTOS);

        return responseDTO;


//
//        ProductResponseDTO responseDTO = new ProductResponseDTO();
//        ProductDTO product = new ProductDTO();
//        product.setDescription((String) resp.get(0).get("description"));
//        if((int)resp.get(0).get("isInStock") == 1) {
//            product.setInStock(true);
//        }
//        else {
//            product.setInStock(false);
//        }
//        product.setSalesPrice((double) resp.get(0).get("salePrice"));
//        product.setTitle((String) resp.get(0).get("name"));
//        responseDTO.setProducts(Arrays.asList(product));
//        return responseDTO;
    }
}
