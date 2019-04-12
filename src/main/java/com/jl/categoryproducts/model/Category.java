package com.jl.categoryproducts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Category {

    private List<Product> products;

    @JsonCreator
    public Category(@JsonProperty("products") List<Product> products) {
        this.products = products;
    }
}
