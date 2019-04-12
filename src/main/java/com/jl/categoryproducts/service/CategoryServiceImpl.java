package com.jl.categoryproducts.service;

import com.jl.categoryproducts.model.Category;
import com.jl.categoryproducts.util.SerializeJsonMessageManager;

public class CategoryServiceImpl {

    public Category getCategoryProducts() {
        return SerializeJsonMessageManager.CATEGORY_PRODUCTS.getDeserializeMessage();
    }
}
