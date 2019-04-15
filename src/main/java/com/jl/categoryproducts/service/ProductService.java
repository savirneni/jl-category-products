package com.jl.categoryproducts.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jl.categoryproducts.backend.model.Product;
import com.jl.categoryproducts.builder.JsonBuilder;
import com.jl.categoryproducts.model.Filter;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private CategoryService categoryService;
    private JsonBuilder jsonBuilder;

    @Autowired
    ProductService(CategoryService categoryService,
                   JsonBuilder jsonBuilder) {
        this.categoryService = categoryService;
        this.jsonBuilder = jsonBuilder;
    }

    public String findReducedProducts(Filter filter) throws JsonProcessingException {
        return jsonBuilder.build(getReducedProducts(), filter);
    }

    protected List<Product> getReducedProducts() {
        List<Product> products = categoryService.getCategoryProducts().getProducts();
        return filterAndSort(products);
    }

    private List<Product> filterAndSort(List<Product> products) {
        //Get list of products where the "was" price is not empty
        List<Product> filteredProducts = products.stream()
                .filter(product -> !product.getPrice().getWas().isEmpty()).collect(Collectors.toList());

        /* construct a map of sorted products. This map is required because the now price in json can be either String or Object
        Eg: now: "15.00" or now: { from: "25.00", to: "35.00" } */
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
