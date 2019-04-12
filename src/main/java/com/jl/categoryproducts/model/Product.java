package com.jl.categoryproducts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Value
public class Product {

    private String productId;
    private String title;
    private List<ColorSwatch> colorSwatches;
    private Price price;

    @JsonCreator
    public Product(@JsonProperty("productId") String productId,
                       @JsonProperty("title") String title,
                       @JsonProperty("colorSwatches") List<ColorSwatch> colorSwatches,
                       @JsonProperty("price") Price price
                   ) {
        this.productId = productId;
        this.title = title;
        this.colorSwatches = colorSwatches;
        this.price = price;
    }
}
