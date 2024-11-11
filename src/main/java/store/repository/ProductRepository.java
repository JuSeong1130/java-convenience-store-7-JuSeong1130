package store.repository;

import store.domain.Product;

import java.util.List;
import java.util.Map;

public class ProductRepository {

    public static final String PRODUCT_NOT_FOUND = "찾는 상품이 없습니다. 다시 입력해 주세요.";
    private final List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    public List<Product> findAll() {
        return products;
    }

    public Product findByName(String name) {
        return products.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(PRODUCT_NOT_FOUND));
    }
}
