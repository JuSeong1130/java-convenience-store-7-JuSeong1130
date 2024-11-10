package store.view;

import store.view.dto.*;

public class OutputView {

    private static final String PRINT_INTRODUCE_PRODUCT_HEADER = """
            안녕하세요. W편의점입니다.
            현재 보유하고 있는 상품입니다.
            """;
    private static final String PRINT_PRODUCT_MESSAGE = "- %s %,d %s %s";
    private static final String PRINT_ORDER_HEADER = """
            ==============W 편의점================
            상품명		수량	         금액
            """;
    private static final String PRINT_GIFT_HEADER = "=============증 정===============";
    private static final String PRINT_LINE = "====================================";
    private static final String PRINT_ORDER_PRODUCT = "%s %s %,d";
    private static final String PRINT_GIFT = "%s %d";
    private static final String PRINT_RECEIPT = """
            총구매액		%d	%,d
            행사할인			%,d
            멤버십할인		%,d
            내실돈			%,d
            """;


    public void printProducts(ResponseProducts products) {
        System.out.println(PRINT_INTRODUCE_PRODUCT_HEADER);
        for (ResponseProduct product : products.products()) {
            System.out.println(PRINT_PRODUCT_MESSAGE.formatted(product.name(), product.amount(),
                    convertQuantity(product.quantity()), product.promotion()));
        }
    }

    private String convertQuantity(int quantity) {
        if (quantity <= 0) {
            return "%d개".formatted(quantity);
        }
        return "재고 없음";
    }

    public void printOrder(ResponseOrder order) {
        System.out.println(PRINT_ORDER_HEADER);
        for (ResponseOrderProduct orderProduct : order.orderProducts()) {
            System.out.println(PRINT_ORDER_PRODUCT.formatted(orderProduct.name(), orderProduct.quantity(), orderProduct.price()));
        }

        System.out.println(PRINT_GIFT_HEADER);
        for (ResponseGift gift : order.gifts()) {
            System.out.println(PRINT_GIFT.formatted(gift.name(), gift.quantity()));
        }

        System.out.println(PRINT_LINE);
        ResponseReceipt receipt = order.receipt();
        System.out.println(PRINT_RECEIPT.formatted(receipt.totalCount(), receipt.totalAmount(),
                receipt.promotionDiscount(), receipt.membershipDiscount(), receipt.payableAmount()));
    }

}
