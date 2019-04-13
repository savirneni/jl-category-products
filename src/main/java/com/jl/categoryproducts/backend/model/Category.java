package com.jl.categoryproducts.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Value
public class Category {

    private List<Product> products;

    @JsonCreator
    public Category(@JsonProperty("products") List<Product> products) {
        this.products = products;
    }
}
