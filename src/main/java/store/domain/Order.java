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

}
