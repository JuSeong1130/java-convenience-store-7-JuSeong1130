package store.service;

import store.repository.ProductRepository;
import store.view.dto.ResponseProducts;

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ResponseProducts findAll() {
        return ResponseProducts.from(productRepository.findAll());
    }
}
