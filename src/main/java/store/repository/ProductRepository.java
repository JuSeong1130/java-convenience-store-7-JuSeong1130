package store.repository;

import store.domain.Product;

import java.util.List;
import java.util.Map;

public class ProductRepository {

    private final List<Product> products;

    public ProductRepository(List<Product> products) {
        this.products = products;
    }

    public List<Product> findAll() {
        return products;
    }

}
