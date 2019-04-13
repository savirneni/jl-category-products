package com.jl.categoryproducts.exception;

public class DeserializeException extends RuntimeException {

    public DeserializeException() {
        super("Exception occurred while deserializing to Object");
    }
}
