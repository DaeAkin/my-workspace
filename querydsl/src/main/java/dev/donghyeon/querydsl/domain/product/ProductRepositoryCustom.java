package dev.donghyeon.querydsl.domain.product;

import dev.donghyeon.querydsl.domain.product.dto.ProductsInCategory;

public interface ProductRepositoryCustom {
    
    ProductsInCategory findProductsBy(Long categoryId);
}
