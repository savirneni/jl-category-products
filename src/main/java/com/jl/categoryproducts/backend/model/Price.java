package com.jl.categoryproducts.backend.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Value;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Value
public class Price {

    private String was;
    private String then1;
    private String then2;
    private Object now;

    @JsonCreator
    public Price(@JsonProperty("was") String was,
                    @JsonProperty("then1") String then1,
                    @JsonProperty("then2") String then2,
                    @JsonProperty("now") Object now) {
        this.was = was;
        this.then1 = then1;
        this.then2 = then2;
        this.now = now;
    }
}
