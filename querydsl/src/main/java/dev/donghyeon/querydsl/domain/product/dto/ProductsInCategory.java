package dev.donghyeon.querydsl.domain.product.dto;

import dev.donghyeon.querydsl.domain.product.Product;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProductsInCategory {
    
    private List<Product> products;
}
