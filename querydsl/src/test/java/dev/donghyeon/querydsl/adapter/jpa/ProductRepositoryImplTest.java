package dev.donghyeon.querydsl.adapter.jpa;

import dev.donghyeon.querydsl.adapter.config.QuerydslConfig;
import dev.donghyeon.querydsl.domain.product.dto.ProductsInCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@Import({QuerydslConfig.class, ProductRepositoryImpl.class})
@ActiveProfiles("test")
@DataJpaTest
//@Sql("classpath:sample-data.sql")
class ProductRepositoryImplTest {

    @Autowired
    ProductRepositoryImpl productRepository;

    

    @Test
    void findProductsBy() {

        ProductsInCategory productsBy = productRepository.findProductsBy(1L);
        System.out.println(productsBy);
    }
}
