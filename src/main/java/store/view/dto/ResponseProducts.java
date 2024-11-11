package store.view.dto;

import store.domain.Product;

import java.util.ArrayList;
import java.util.List;

public record ResponseProducts(List<ResponseProduct> products) {

    public static ResponseProducts from(List<Product> products) {
        List<ResponseProduct> responseProducts = new ArrayList<>();
        for (Product product : products) {
            responseProducts.add(new ResponseProduct(product.getName(), product.getPrice(), product.getPromotionQuantity(), product.getPromotionName()));
            responseProducts.add(new ResponseProduct(product.getName(), product.getPrice(), product.getCommonQuantity(), ""));
        }
        return new ResponseProducts(responseProducts);
    }

}
