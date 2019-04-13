package com.jl.categoryproducts.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jl.categoryproducts.exception.DeserializeException;
import com.jl.categoryproducts.exception.SerializeException;
import com.jl.categoryproducts.backend.model.Category;

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
