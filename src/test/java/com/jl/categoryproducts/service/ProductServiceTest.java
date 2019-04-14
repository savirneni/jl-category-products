package com.jl.categoryproducts.service;

import static com.jl.categoryproducts.util.SerializeJsonMessageTestManager.*;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jl.categoryproducts.backend.model.Product;
import com.jl.categoryproducts.builder.JsonBuilder;
import com.jl.categoryproducts.model.Filter;
import com.jl.categoryproducts.model.LabelType;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ProductServiceTest {

    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductService(new CategoryService(), new JsonBuilder());
    }

    @Test
    public void getReducedProductsTest() {
        List<Product> reducedProducts = productService.getReducedProducts();

        assertNotNull(reducedProducts);
        assertTrue(!reducedProducts.get(0).getPrice().getWas().isEmpty());
        //total reduced products
        assertTrue(reducedProducts.size() == 6);
        //highest reduced product
        assertEquals("3428696", reducedProducts.get(0).getProductId());
        //lowest reduced product
        assertEquals("3341058", reducedProducts.get(5).getProductId());
    }

    @Test
    public void findReducedProductsWithQPDefaultTest() throws JsonProcessingException {
        Filter filter = Filter.builder().build();

        String reducedProductsJson = productService.findReducedProducts(filter);

        assertNotNull(reducedProductsJson);
        assertJsonEquals(EXPECTED_REDUCED_PRODUCTS_QP_DEFAULT.getSerializedMessage(), reducedProductsJson);
    }

    @Test
    public void findReducedProductsWithQPShowWasThenNowTest() throws JsonProcessingException {
        Filter filter = Filter.builder().labelType(LabelType.SHOWWASTHENNOW).build();

        String reducedProductsJson = productService.findReducedProducts(filter);

        assertNotNull(reducedProductsJson);
        assertJsonEquals(EXPECTED_REDUCED_PRODUCTS_QP_SHOWWASTHENNOW.getSerializedMessage(), reducedProductsJson);
    }

    @Test
    public void findReducedProductsWithQPShowPercentDiscountTest() throws JsonProcessingException {
        Filter filter = Filter.builder().labelType(LabelType.SHOWPERCENTDISCOUNT).build();

        String reducedProductsJson = productService.findReducedProducts(filter);

        assertNotNull(reducedProductsJson);
        assertJsonEquals(EXPECTED_REDUCED_PRODUCTS_QP_SHOWPERCENTDISCOUNT.getSerializedMessage(), reducedProductsJson);
    }

}