package com.jl.categoryproducts.builder;

import static com.jl.categoryproducts.util.SerializeJsonMessageTestManager.*;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jl.categoryproducts.backend.model.Category;

import org.junit.Before;
import org.junit.Test;



public class JsonBuilderTest {

    private JsonBuilder jsonBuilder;

    @Before
    public void setUp() {
        jsonBuilder = new JsonBuilder();
    }

    @Test
    public void reducedProductsJsonBuildTest() throws JsonProcessingException {
        Category category = REDUCED_PRODUCTS.getDeserializeMessage();
        String json = jsonBuilder.build(category.getProducts());

        assertJsonEquals(EXPECTED_REDUCED_PRODUCTS.getSerializedMessage(), json);

    }
}
