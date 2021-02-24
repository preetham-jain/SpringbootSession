package com.example.SpringSession.service.impl;

import com.example.SpringSession.client.SearchClient;
import com.example.SpringSession.dto.ProductDTO;
import com.example.SpringSession.dto.ProductRequestDTO;
import com.example.SpringSession.dto.ProductResponseDTO;
import com.example.SpringSession.service.ProductService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.*;

@Service
public class ProductServiceimpl implements ProductService, Callable<List<ProductDTO>> {

    @Autowired
    private SearchClient searchClient;
    @Override
    public ProductResponseDTO getProducts(ProductRequestDTO request) {

        ProductResponseDTO responseDTO = new ProductResponseDTO();
        ExecutorService threadPool = Executors.newFixedThreadPool(2);

        threadPool.execute(() -> {
//            System.out.println(Thread.currentThread().getName());
            String searchTermQuery = request.getSearchTerm();
            List<ProductDTO> productDTOS = getSearchTermBaseProducts(searchTermQuery);
            responseDTO.setProducts(productDTOS);
        });

        threadPool.execute(() -> {
//            System.out.println(Thread.currentThread().getName());
            String locationQuery = "stockLocation" + ":\"" + request.getStockLocation() + "\"";
            List<ProductDTO> locationProductDTOs = getSearchTermBaseProducts(locationQuery);
            responseDTO.setLocationBasedProducts(locationProductDTOs);
        });

        awaitTerminationAfterShutdown(threadPool);
        return responseDTO;

//        String searchTermQuery = request.getSearchTerm();
//        String locationQuery = "stockLocation: " + request.getStockLocation();
//        ProductResponseDTO responseDTO = new ProductResponseDTO();
//
//        ExecutorService executorService = Executors.newFixedThreadPool(2);
//
//        Set<Callable<List<ProductDTO>>> callables = new HashSet<Callable<List<ProductDTO>>>();
//
//        callables.add(new Callable<List<ProductDTO>>() {
//            @Override
//            public List<ProductDTO> call() throws Exception {
//                List<ProductDTO> productDTOS = getSearchTermBaseProducts(searchTermQuery);
//                return productDTOS;
//            }
//        });
//        callables.add(new Callable<List<ProductDTO>>() {
//            @Override
//            public List<ProductDTO> call() throws Exception {
//                List<ProductDTO> locationProductDTOS = getSearchTermBaseProducts(locationQuery);
//                return locationProductDTOS;
//            }
//        });
//
//        try {
//            List<Future<List<ProductDTO>>> futures = executorService.invokeAll(callables);
//            for(Future<List<ProductDTO>> future: futures) {
//
//                responseDTO.setProducts(productDTOS);
//                responseDTO.setLocationBasedProducts(locationProductDTOS);
//                System.out.println(future.get());
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }
//
//
//
//
//        return responseDTO;
    }

    private void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(60, TimeUnit.SECONDS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private List<ProductDTO> getSearchTermBaseProducts(String query) {
        Map<String, Object> products = searchClient.getProducts(query);
        List<Map<String, Object>> resp = (List<Map<String, Object>>) ((Map) products.get("response")).get("docs");
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Map<String, Object> productClientResponse : resp) {
            String title = (String) productClientResponse.get("name");
            boolean inStock = (int) productClientResponse.get("isInStock") == 1 ? true : false;
            String description = (String) productClientResponse.get("description");
//            double salePrice = (double) productClientResponse.get("salePrice");
            ProductDTO productDTO = new ProductDTO();
//            productDTO.setSalesPrice(salePrice);
            productDTO.setInStock(inStock);
            productDTO.setTitle(title);
            productDTO.setDescription(description);

            productDTOS.add(productDTO);
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> call() throws Exception {
        return null;
    }
}
