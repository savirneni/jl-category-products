package com.jl.categoryproducts.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class ColorSwatch {

    private String color;
    private String rgbColor;
    private String skuId;

}
