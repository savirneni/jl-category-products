package com.jl.categoryproducts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Product {

    private String productId;
    private String title;
    private List<ColorSwatch> colorSwatches;
    private Price price;

    @JsonCreator
    public Product(@JsonProperty("productId") String productId,
                       @JsonProperty("title") String title,
                       @JsonProperty("colorSwatches") List<ColorSwatch> colorSwatches,
                       @JsonProperty("price") Price price) {
        this.productId = productId;
        this.title = title;
        this.colorSwatches = colorSwatches;
        this.price = price;
    }
}
