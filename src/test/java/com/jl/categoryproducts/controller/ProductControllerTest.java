package com.jl.categoryproducts.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import com.jl.categoryproducts.exception.EnumNotFoundException;
import com.jl.categoryproducts.model.Filter;
import com.jl.categoryproducts.model.LabelType;
import com.jl.categoryproducts.service.ProductService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {

    private static final String JSON = "{\"products\":[{\"productId\":\"3428696\"}]}";

    @Mock
    private ProductService productService;

    private ProductController controller;

    @Before
    public void setUp() {
        controller = new ProductController(productService);
    }

    @Test
    public void findReducedProductsWithNullQueryParameter() {
        when(productService.findReducedProducts(Filter.builder().build())).thenReturn(JSON);

        ResponseEntity<String> reducedProducts = controller.findReducedProducts(null);

        assertThat(reducedProducts.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(reducedProducts.getBody(), equalTo(JSON));
    }

    @Test
    public void findReducedProductsWithQueryParameter() {
        when(productService.findReducedProducts(Filter.builder().labelType(LabelType.SHOWWASTHENNOW).build())).thenReturn(JSON);

        ResponseEntity<String> reducedProducts = controller.findReducedProducts("ShowWasThenNow");

        assertThat(reducedProducts.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(reducedProducts.getBody(), equalTo(JSON));
    }

    @Test(expected = EnumNotFoundException.class)
    public void findReducedProductsWithInvalidQueryParameter() {

        controller.findReducedProducts("InvalidLabelType");

    }

}