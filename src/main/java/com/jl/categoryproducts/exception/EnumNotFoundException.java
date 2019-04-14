package com.jl.categoryproducts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid labelType parameter value")
public class EnumNotFoundException extends RuntimeException {

    public EnumNotFoundException(String labelType) {
        super(String.format("labelType %s not found", labelType));
    }

}
