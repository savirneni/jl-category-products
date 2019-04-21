package com.jl.categoryproducts.util;

public class DeserializeException extends RuntimeException {

    public DeserializeException() {
        super("Exception occurred while deserializing to Object");
    }
}
