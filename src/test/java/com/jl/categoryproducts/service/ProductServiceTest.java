package com.jl.categoryproducts.service;

import static com.jl.categoryproducts.util.SerializeJsonMessageTestManager.*;
import static net.javacrumbs.jsonunit.JsonAssert.assertJsonEquals;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import com.jl.categoryproducts.backend.model.Category;
import com.jl.categoryproducts.backend.model.Product;
import com.jl.categoryproducts.builder.JsonBuilder;
import com.jl.categoryproducts.model.Filter;
import com.jl.categoryproducts.model.LabelType;
import com.jl.categoryproducts.util.SerializeJsonMessageTestManager;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private ProductService productService;

    @Mock
    private CategoryService categoryService;

    @Before
    public void setUp() {
        productService = new ProductService(categoryService, new JsonBuilder());

        Category category = SerializeJsonMessageTestManager.CATEGORY_PRODUCTS.getDeserializeMessage();
        when(categoryService.getCategoryProducts()).thenReturn(category);
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
    public void findReducedProductsWithQPDefaultTest() {
        Filter filter = Filter.builder().build();

        String reducedProductsJson = productService.findReducedProducts(filter);

        assertNotNull(reducedProductsJson);
        assertJsonEquals(EXPECTED_REDUCED_PRODUCTS_QP_DEFAULT.getSerializedMessage(), reducedProductsJson);
    }

    @Test
    public void findReducedProductsWithQPShowWasThenNowTest() {
        Filter filter = Filter.builder().labelType(LabelType.SHOWWASTHENNOW).build();

        String reducedProductsJson = productService.findReducedProducts(filter);

        assertNotNull(reducedProductsJson);
        assertJsonEquals(EXPECTED_REDUCED_PRODUCTS_QP_SHOWWASTHENNOW.getSerializedMessage(), reducedProductsJson);
    }

    @Test
    public void findReducedProductsWithQPShowPercentDiscountTest() {
        Filter filter = Filter.builder().labelType(LabelType.SHOWPERCENTDISCOUNT).build();

        String reducedProductsJson = productService.findReducedProducts(filter);

        assertNotNull(reducedProductsJson);
        assertJsonEquals(EXPECTED_REDUCED_PRODUCTS_QP_SHOWPERCENTDISCOUNT.getSerializedMessage(), reducedProductsJson);
    }

    @Test
    public void findReducedProductsWhenProductsAreEmptyTest() {
        when(categoryService.getCategoryProducts()).thenReturn(Category.builder().products(new ArrayList<>()).build());

        Filter filter = Filter.builder().build();

        String reducedProductsJson = productService.findReducedProducts(filter);

        assertNotNull(reducedProductsJson);
        assertJsonEquals("{}", reducedProductsJson);
    }

}