package com.jl.categoryproducts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Value
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
