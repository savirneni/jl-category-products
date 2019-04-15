package com.jl.categoryproducts.builder;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jl.categoryproducts.model.*;
import com.jl.categoryproducts.util.RGBColourUtil;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class JsonBuilder {

    private static final String WAS = "Was £";
    private static final String THEN = ", then £";
    private static final String NOW = ", now £";
    private static final String PERCENT = " off - now £";

    public String build(List<com.jl.categoryproducts.backend.model.Product> products, Filter filter) {

        List<Product> reducedProducts = new ArrayList<>();
        if (products != null && !products.isEmpty()) {
            for (com.jl.categoryproducts.backend.model.Product product : products) {

                Product rProduct = Product.builder()
                        .productId(product.getProductId())
                        .title(product.getTitle())
                        .colorSwatches(buildColorSwatch(product.getColorSwatches()))
                        .nowPrice(buildNowPrice(product))
                        .priceLabel(buildPriceLabel(product, filter))
                        .build();

                reducedProducts.add(rProduct);
            }
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        try {
            return objectMapper.writeValueAsString(Products.builder().products(reducedProducts).build());
        } catch (JsonProcessingException je) {
            return "{}";
        }
    }

    private List<ColorSwatch> buildColorSwatch(List<com.jl.categoryproducts.backend.model.ColorSwatch> colorSwatches) {
        List<ColorSwatch> rColorSwatches = new ArrayList<>();
        if (colorSwatches != null && !colorSwatches.isEmpty()) {
            for (com.jl.categoryproducts.backend.model.ColorSwatch colorSwatch : colorSwatches) {
                ColorSwatch rColorSwatch = ColorSwatch.builder()
                        .color(colorSwatch.getColor())
                        .rgbColor(RGBColourUtil.get(colorSwatch.getBasicColor()))
                        .skuId(colorSwatch.getSkuId())
                        .build();
                rColorSwatches.add(rColorSwatch);
            }
        }
        return rColorSwatches;
    }

    private String buildNowPrice(com.jl.categoryproducts.backend.model.Product product) {
        return "£" + getNowPrice(product);
    }

    private String formatPrice(String now) {
        int intNowPrice = new BigDecimal(now).intValue();
        return intNowPrice < 10 ? new BigDecimal(now).toString() : String.valueOf(intNowPrice);
    }

    private String getNowPrice(com.jl.categoryproducts.backend.model.Product product) {
        String now;
        if (product.getPrice().getNow() instanceof  String) {
            now = (String) product.getPrice().getNow();
        } else {
            Map<String, String> hashMap = (LinkedHashMap<String, String>)product.getPrice().getNow();
            now = hashMap.get("to");
        }
        return formatPrice(now);
    }

    private String buildPriceLabel(com.jl.categoryproducts.backend.model.Product product, Filter filter) {
        if (LabelType.SHOWPERCENTDISCOUNT.equals(filter.getLabelType())) {
            return buildPercentDiscount(product);
        } else if (LabelType.SHOWWASTHENNOW.equals(filter.getLabelType())) {
            return buildWasThenNowPrice(product);
        } else {
            return buildWasNowPrice(product);
        }
    }

    private String buildPercentDiscount(com.jl.categoryproducts.backend.model.Product product) {
        BigDecimal percentDiscount = (new BigDecimal(product.getPrice().getWas()).subtract(new BigDecimal(getNowPrice
                (product)))).divide(new BigDecimal(product.getPrice().getWas()), 2);

        return NumberFormat.getPercentInstance().format(percentDiscount) + PERCENT + getNowPrice(product);
    }

    private String buildWasThenNowPrice(com.jl.categoryproducts.backend.model.Product product) {
        String then2 = product.getPrice().getThen2();
        String then = "".equals(then2) ? product.getPrice().getThen1() : then2 ;

        if ("".equals(then)) {
            return buildWasNowPrice(product);
        } else {
            return WAS + formatPrice(product.getPrice().getWas()) + THEN + formatPrice(then) + NOW + getNowPrice(product);
        }

    }

    private String buildWasNowPrice(com.jl.categoryproducts.backend.model.Product product) {
        return WAS + formatPrice(product.getPrice().getWas()) + NOW + getNowPrice(product);
    }

}
