package store.view;

import store.view.dto.ResponseProduct;
import store.view.dto.ResponseProducts;

public class OutputView {

    private static final String INTRODUCE_PRODUCT_HEADER = """
            안녕하세요. W편의점입니다.
            현재 보유하고 있는 상품입니다.
            """;
    private static final String PRODUCT_MESSAGE = "- %s %,d %s %s";

    public void printProducts(ResponseProducts products) {
        System.out.println(INTRODUCE_PRODUCT_HEADER);
        for (ResponseProduct product : products.products()) {
            System.out.println(PRODUCT_MESSAGE.formatted(product.name(), product.amount(),
                    convertQuantity(product.quantity()), product.promotion()));
        }
    }

    private String convertQuantity(int quantity) {
        if(quantity <= 0) {
            return "%d개".formatted(quantity);
        }
        return "재고 없음";
    }

}
