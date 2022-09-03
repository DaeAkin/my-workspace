package dev.donghyeon.querydsl.domain.product;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

import static lombok.AccessLevel.PROTECTED;

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal price;
    
    private Long categoryId;

    public Product(String name, BigDecimal price, Long categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }
}
