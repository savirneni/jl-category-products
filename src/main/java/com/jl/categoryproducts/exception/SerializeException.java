package com.jl.categoryproducts.exception;

public class SerializeException extends RuntimeException {

    public SerializeException() {
        super("Exception occurred while serializing the json file");
    }
}
