package com.jl.categoryproducts.builder;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jl.categoryproducts.model.ColorSwatch;
import com.jl.categoryproducts.model.Product;
import com.jl.categoryproducts.model.Products;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class JsonBuilder {

    public String build(List<com.jl.categoryproducts.backend.model.Product> products) throws JsonProcessingException {

        List<Product> reducedProducts = new ArrayList<>();
        if (products != null && !products.isEmpty()) {
            for (com.jl.categoryproducts.backend.model.Product product : products) {

                Product rProduct = Product.builder()
                        .productId(product.getProductId())
                        .title(product.getTitle())
                        .colorSwatches(buildColorSwatch(product.getColorSwatches()))
                        .nowPrice(buildNowPrice(product))
                        .priceLabel(product.getPrice().getWas())
                        .build();

                reducedProducts.add(rProduct);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        return objectMapper.writeValueAsString(Products.builder().products(reducedProducts).build());
    }

    private List<ColorSwatch> buildColorSwatch(List<com.jl.categoryproducts.backend.model.ColorSwatch> colorSwatches) {
        List<ColorSwatch> rColorSwatches = new ArrayList<>();
        if (colorSwatches != null && !colorSwatches.isEmpty()) {
            for (com.jl.categoryproducts.backend.model.ColorSwatch colorSwatch : colorSwatches) {
                ColorSwatch rColorSwarch = ColorSwatch.builder()
                        .color(colorSwatch.getColor())
                        .rgbColor(colorSwatch.getBasicColor())
                        .skuId(colorSwatch.getSkuId())
                        .build();
                rColorSwatches.add(rColorSwarch);
            }
        }
        return rColorSwatches;
    }

    private String buildNowPrice(com.jl.categoryproducts.backend.model.Product product) {
        String now;
        if (product.getPrice().getNow() instanceof  String) {
            now = (String) product.getPrice().getNow();
        } else {
            Map<String, String> hashMap = (LinkedHashMap<String, String>)product.getPrice().getNow();
            now = hashMap.get("to");
        }
        int intNowPrice = new BigDecimal(now).intValue();
        String price =  intNowPrice < 10 ? new BigDecimal(now).toString() : String.valueOf(intNowPrice);
        return "£" + price;
    }
}
