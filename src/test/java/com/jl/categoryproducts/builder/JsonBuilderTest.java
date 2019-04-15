package com.jl.categoryproducts.builder;

import static com.jl.categoryproducts.util.SerializeJsonMessageTestManager.EXPECTED_REDUCED_PRODUCTS;
import static com.jl.categoryproducts.util.SerializeJsonMessageTestManager.REDUCED_PRODUCTS;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;

import com.jl.categoryproducts.backend.model.Category;
import com.jl.categoryproducts.model.Filter;

import org.junit.Before;
import org.junit.Test;



public class JsonBuilderTest {

    private JsonBuilder jsonBuilder;

    @Before
    public void setUp() {
        jsonBuilder = new JsonBuilder();
    }

    @Test
    public void reducedProductsJsonBuildTest() {
        Category category = REDUCED_PRODUCTS.getDeserializeMessage();
        String json = jsonBuilder.build(category.getProducts(), Filter.builder().build());

        assertJsonEquals(EXPECTED_REDUCED_PRODUCTS.getSerializedMessage(), json);

    }
}
