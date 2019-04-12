package com.jl.categoryproducts.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Price {

    private String was;
    private String then1;
    private String then2;
    private String now;

    @JsonCreator
    public Price(@JsonProperty("was") String was,
                    @JsonProperty("then1") String then1,
                    @JsonProperty("then2") String then2,
                    @JsonProperty("now") String now) {
        this.was = was;
        this.then1 = then1;
        this.then2 = then2;
        this.now = now;
    }
}
