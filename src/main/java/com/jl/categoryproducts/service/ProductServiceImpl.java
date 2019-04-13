package com.jl.categoryproducts.service;

import com.jl.categoryproducts.model.Product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl {

    private CategoryServiceImpl categoryService;

    @Autowired
    public ProductServiceImpl(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    public List<Product> getReducedProducts() {
        return new ArrayList<>();
    }
}
