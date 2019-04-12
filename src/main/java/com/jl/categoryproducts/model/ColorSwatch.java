package com.jl.categoryproducts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ColorSwatch {

    private String color;
    private String basicColor;
    private String skuId;

    @JsonCreator
    public ColorSwatch(@JsonProperty("color") String color,
                 @JsonProperty("basicColor") String basicColor,
                 @JsonProperty("skuId") String skuId) {
        this.color = color;
        this.basicColor = basicColor;
        this.skuId = skuId;
    }
}
