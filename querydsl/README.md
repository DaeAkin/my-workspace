# querydsl에서 fk가 없어도 잘 join 해오는지 궁금해서 해봄

```java

@Entity
@NoArgsConstructor(access = PROTECTED)
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private BigDecimal price;
    
    private Long categoryId;
}
```

```java
@Entity
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Category {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
}
```

```java
    public ProductsInCategory findProductsBy(Long categoryId) {
        List<Product> products = queryFactory.select(product)
                .where(product.categoryId.eq(categoryId))
                .from(product)
                .fetch();
        return new ProductsInCategory(products);
    }
```
