package com.jl.categoryproducts.model;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Product {

    private String productId;
    private String title;
    private List<ColorSwatch> colorSwatches;
    private String nowPrice;
    private String priceLabel;
}
