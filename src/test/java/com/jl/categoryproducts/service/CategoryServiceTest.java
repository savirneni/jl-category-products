package com.jl.categoryproducts.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.jl.categoryproducts.backend.model.Category;
import com.jl.categoryproducts.backend.model.Product;
import com.jl.categoryproducts.util.SerializeJsonMessageTestManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

    @Mock
    private RestTemplate restTemplate;

    private CategoryService categoryService;

    @Before
    public void setUp() {
        categoryService = new CategoryService(restTemplate, "http");
    }

    @Test
    public void getCategoryProductsTest() {

        String jsonResponse = SerializeJsonMessageTestManager.CATEGORY_PRODUCTS.getSerializedMessage();
        ResponseEntity<String> responseEntity = new ResponseEntity<>(jsonResponse, HttpStatus.ACCEPTED);
        when(restTemplate.getForEntity("http", String.class)).thenReturn(responseEntity);

        Category category = categoryService.getCategoryProducts();
        assertNotNull(category);
        assertNotEquals(0, category.getProducts().size());
        assertEquals(50, category.getProducts().size());

        Product product = category.getProducts().get(0);
        assertEquals("3525081", product.getProductId());
    }
}
