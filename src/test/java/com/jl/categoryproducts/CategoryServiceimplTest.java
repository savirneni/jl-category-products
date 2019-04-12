package com.jl.categoryproducts;

import static org.junit.Assert.*;

import com.jl.categoryproducts.model.Category;
import com.jl.categoryproducts.model.Product;
import com.jl.categoryproducts.service.CategoryServiceImpl;

import org.junit.Before;
import org.junit.Test;

public class CategoryServiceimplTest {

    private CategoryServiceImpl categoryServiceImpl;

    @Before
    public void setUp() {
        categoryServiceImpl = new CategoryServiceImpl();
    }

    @Test
    public void getCategoryProductsTest() {
        Category category = categoryServiceImpl.getCategoryProducts();
        assertNotNull(category);
        assertNotEquals(0, category.getProducts().size());
        assertEquals(50, category.getProducts().size());

        Product product = category.getProducts().get(0);
        assertEquals("3525081", product.getProductId());
    }
}
