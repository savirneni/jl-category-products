package com.jl.categoryproducts.util;

public class SerializeException extends RuntimeException {

    public SerializeException() {
        super("Exception occurred while serializing the json file");
    }
}
