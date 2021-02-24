package com.example.SpringSession.service.impl;

import com.example.SpringSession.client.SearchClient;
import com.example.SpringSession.dto.ProductRequestDTO;
import com.example.SpringSession.dto.ProductResponseDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class ProductServiceimplTest {

    @InjectMocks
    private ProductServiceimpl productService;

    @Mock
    private SearchClient searchClient;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    public void teardown() {

    }

    @org.junit.jupiter.api.Test
    void getProducts() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> searchTermMockObject = objectMapper.readValue( new URL("file:src/test/resources/search.mock"), Map.class);

        Map<String, Object> locationMockObject = objectMapper.readValue( new URL("file:src/test/resources/location.mock"), Map.class);

        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:\"Jakarta\"")).thenReturn(locationMockObject);

        ProductRequestDTO requestDTO = new ProductRequestDTO();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setStockLocation("Jakarta");
        ProductResponseDTO responseDTO =  productService.getProducts(requestDTO);

        assertEquals(responseDTO.getProducts().size(), 10);
        assertEquals(responseDTO.getLocationBasedProducts().size(), 10);

        Mockito.verify(searchClient).getProducts("samsung");

    }

    @Test
    public void testGetProductsExceptionTest() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> searchTermMockObject = objectMapper.readValue( new URL("file:src/test/resources/search.mock"), Map.class);

        Mockito.when(searchClient.getProducts("samsung")).thenReturn(searchTermMockObject);
        Mockito.when(searchClient.getProducts("stockLocation:\"Jakarta\"")).thenThrow(NullPointerException.class);

        ProductRequestDTO requestDTO = new ProductRequestDTO();
        requestDTO.setSearchTerm("samsung");
        requestDTO.setStockLocation("Jakarta");
        ProductResponseDTO responseDTO =  productService.getProducts(requestDTO);

        assertEquals(responseDTO.getProducts().size(), 10);
        assertEquals(responseDTO.getLocationBasedProducts(), null);

        Mockito.verify(searchClient).getProducts("samsung");
    }

}