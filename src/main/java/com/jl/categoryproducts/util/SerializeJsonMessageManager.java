package com.jl.categoryproducts.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jl.categoryproducts.model.Category;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum SerializeJsonMessageManager {

    CATEGORY_PRODUCTS("categoryproducts.json");

    private final String serializedMessage;

    SerializeJsonMessageManager(String fileName) {
        this.serializedMessage = getSerialisedMessage(fileName);
    }

    private String getSerialisedMessage(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/main/resources/" + fileName)));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    public Category getDeserializeMessage() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        try {
            return mapper.readValue(this.serializedMessage, Category.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
