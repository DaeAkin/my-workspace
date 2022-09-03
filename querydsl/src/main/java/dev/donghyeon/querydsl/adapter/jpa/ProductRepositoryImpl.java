package dev.donghyeon.querydsl.adapter.jpa;

import com.querydsl.jpa.impl.JPAQueryFactory;

import dev.donghyeon.querydsl.domain.product.Product;
import dev.donghyeon.querydsl.domain.product.ProductRepositoryCustom;
import dev.donghyeon.querydsl.domain.product.dto.ProductsInCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static dev.donghyeon.querydsl.domain.product.QProduct.product;

@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    
    @Override
    public ProductsInCategory findProductsBy(Long categoryId) {
        List<Product> products = queryFactory.select(product)
                .where(product.categoryId.eq(categoryId))
                .from(product)
                .fetch();
        return new ProductsInCategory(products);
    }
}
