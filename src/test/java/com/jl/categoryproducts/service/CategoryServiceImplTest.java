package com.jl.categoryproducts.service;

import static org.junit.Assert.*;

import com.jl.categoryproducts.backend.model.Category;
import com.jl.categoryproducts.backend.model.Product;
import com.jl.categoryproducts.service.CategoryServiceImpl;

import org.junit.Before;
import org.junit.Test;


public class CategoryServiceImplTest {

    private CategoryServiceImpl categoryService;

    @Before
    public void setUp() {
        categoryService = new CategoryServiceImpl();
    }

    @Test
    public void getCategoryProductsTest() {
        Category category = categoryService.getCategoryProducts();
        assertNotNull(category);
        assertNotEquals(0, category.getProducts().size());
        assertEquals(50, category.getProducts().size());

        Product product = category.getProducts().get(0);
        assertEquals("3525081", product.getProductId());
    }
}
