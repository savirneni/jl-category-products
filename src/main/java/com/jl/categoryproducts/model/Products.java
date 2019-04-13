package com.jl.categoryproducts.model;

import java.util.List;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Products {
    private List<Product> products;
}
