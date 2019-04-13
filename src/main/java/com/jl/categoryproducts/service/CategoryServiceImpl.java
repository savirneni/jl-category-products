package com.jl.categoryproducts.service;

import com.jl.categoryproducts.model.Category;
import com.jl.categoryproducts.util.SerializeJsonMessageManager;

import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl {

    public Category getCategoryProducts() {
        return SerializeJsonMessageManager.CATEGORY_PRODUCTS.getDeserializeMessage();
    }
}
