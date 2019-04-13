package com.jl.categoryproducts.service;

import com.jl.categoryproducts.model.Product;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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
        List<Product> products = categoryService.getCategoryProducts().getProducts();
        return filterAndSort(products);
    }

    private List<Product> filterAndSort(List<Product> products) {
        List<Product> filteredProducts = products.stream()
                .filter(product -> !product.getPrice().getWas().isEmpty()).collect(Collectors.toList());

        Map<BigDecimal, Product> sortedReducedProducts = new TreeMap<>(Collections.reverseOrder());
        for(Product product : filteredProducts) {
            String now;
            if (product.getPrice().getNow() instanceof  String) {
                now = (String) product.getPrice().getNow();
            } else {
                Map<String, String> hashMap = (LinkedHashMap<String, String>)product.getPrice().getNow();
                now = hashMap.get("to");
            }
            sortedReducedProducts.put(new BigDecimal(product.getPrice().getWas()).subtract(new BigDecimal(now)), product);
        }

        return new ArrayList<>(sortedReducedProducts.values());

    }
}
