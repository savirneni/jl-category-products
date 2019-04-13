package com.jl.categoryproducts;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.jl.categoryproducts.model.Product;
import com.jl.categoryproducts.service.CategoryServiceImpl;
import com.jl.categoryproducts.service.ProductServiceImpl;

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
        List<Product> reducedProducts = productService.getReducedProducts();
        assertNotNull(reducedProducts);
        assertTrue(!reducedProducts.get(0).getPrice().getWas().isEmpty());
        assertTrue(reducedProducts.size() == 6);
    }

}