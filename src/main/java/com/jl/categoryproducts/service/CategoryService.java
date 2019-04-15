package com.jl.categoryproducts.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jl.categoryproducts.backend.model.Category;
import com.jl.categoryproducts.exception.DeserializeException;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CategoryService {

    private RestTemplate restTemplate;

    private String url;

    @Autowired
    CategoryService(RestTemplate restTemplate, @Value("${service.url}") String url) {
        this.restTemplate = restTemplate;
        this.url = url;
    }

    public Category getCategoryProducts() {

        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        try {
            return new ObjectMapper().readValue(response.getBody(), Category.class);
        } catch (IOException ie) {
            throw new DeserializeException();
        }
    }
}
