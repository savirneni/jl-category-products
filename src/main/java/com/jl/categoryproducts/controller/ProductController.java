package com.jl.categoryproducts.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.ResponseEntity.ok;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import com.jl.categoryproducts.exception.EnumNotFoundException;
import com.jl.categoryproducts.model.Filter;
import com.jl.categoryproducts.model.LabelType;
import com.jl.categoryproducts.service.ProductService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ApiOperation(value = "findReducedProducts",
        notes = "Returns an array of the reduced products",
        response = String.class, responseContainer = "Reduced product details")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Products", response = String.class)})
    @RequestMapping(value = "/categories/{categoryId}/products", method = GET, produces = APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> findReducedProducts(
            @RequestParam(value = "labelType", required = false) String labelType) {

        LabelType labelTypeParam;
        try {
            labelTypeParam = labelType != null ? LabelType.valueOf(labelType.toUpperCase()) : null;
        } catch(IllegalArgumentException ie) {
            throw new EnumNotFoundException(labelType);
        }

        return ok(productService.findReducedProducts(Filter.builder().labelType(labelTypeParam).build()));
    }
}
