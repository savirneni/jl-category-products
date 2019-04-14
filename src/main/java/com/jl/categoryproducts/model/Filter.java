package com.jl.categoryproducts.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Filter {
    private LabelType labelType;
}
