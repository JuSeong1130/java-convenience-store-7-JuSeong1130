package store.domain;

import java.util.List;

public class Order {

    private final List<OrderProduct> orderProducts;
    private OrderState orderState;
    private boolean hasMembershipDiscount;

    public Order(List<OrderProduct> orderProducts, OrderState orderState) {
        this.orderProducts = orderProducts;
        this.orderState = orderState;
        this.hasMembershipDiscount = false;
    }

    public void solvePending(String name,Command command) {
        OrderProduct orderProduct = orderProducts.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst().orElseThrow(() -> new IllegalArgumentException("잘못된 상품명을 입력하셨습니다"));
        orderProduct.solvePending(command);
    }

}
