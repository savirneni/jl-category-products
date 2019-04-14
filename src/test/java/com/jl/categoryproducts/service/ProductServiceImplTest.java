package com.jl.categoryproducts.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.jl.categoryproducts.backend.model.Product;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class ProductServiceImplTest {

    private ProductServiceImpl productService;

    @Before
    public void setUp() {
        productService = new ProductServiceImpl(new CategoryServiceImpl());
    }

    @Test
    public void getReducedProductsTest() {
        List<Product> sortedReducedProducts = productService.getReducedProducts();
        assertNotNull(sortedReducedProducts);
        assertTrue(!sortedReducedProducts.get(0).getPrice().getWas().isEmpty());
        //total reduced products
        assertTrue(sortedReducedProducts.size() == 6);
        //highest reduced product
        assertEquals("3428696", sortedReducedProducts.get(0).getProductId());
        //lowest reduced product
        assertEquals("3341058", sortedReducedProducts.get(5).getProductId());
    }

}