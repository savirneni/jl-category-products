package com.jl.categoryproducts.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jl.categoryproducts.backend.model.Category;
import com.jl.categoryproducts.exception.DeserializeException;
import com.jl.categoryproducts.exception.SerializeException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum SerializeJsonMessageTestManager {

    CATEGORY_PRODUCTS("categoryProducts.json"),
    REDUCED_PRODUCTS("reducedProducts.json"),
    EXPECTED_REDUCED_PRODUCTS("expectedReducedProducts.json"),

    EXPECTED_REDUCED_PRODUCTS_QP_DEFAULT("expectedReducedProductsWithQPDefault.json"),
    EXPECTED_REDUCED_PRODUCTS_QP_SHOWWASTHENNOW("expectedReducedProductsWithQPShowWasThenNow.json"),
    EXPECTED_REDUCED_PRODUCTS_QP_SHOWPERCENTDISCOUNT("expectedReducedProductsWithQPShowPercentDiscount.json");

    private final String serializedMessage;

    SerializeJsonMessageTestManager(String fileName) {
        this.serializedMessage = getSerialisedMessage(fileName);
    }

    private String getSerialisedMessage(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/" + fileName)));
        } catch (IOException e) {
            throw new SerializeException();
        }
    }

    public Category getDeserializeMessage() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            return mapper.readValue(this.serializedMessage, Category.class);
        } catch (IOException e) {
            throw new DeserializeException();
        }
    }

}
