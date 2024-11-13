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

    public void activeMembership() {
        this.hasMembershipDiscount = true;
        this.orderState = OrderState.SUCCESS;
    }

    public OrderReceipt getReceipt() {
        List<OrderGift> orderGifts = getOrderGifts();
        OrderPayment orderPayment = getOrderPayment();
        return new OrderReceipt(orderProducts, orderGifts, orderPayment);
    }

    private OrderPayment getOrderPayment() {
        int totalAmount = calculateTotalAmount();
        int promotionDiscount = calculatePromotionDiscount();
        int memberShipDiscount = calculateMembershipDiscount(totalAmount, promotionDiscount);
        int payment = totalAmount - promotionDiscount - memberShipDiscount;
        int totalCount = calculateTotalCount();

        return new OrderPayment(totalCount, totalAmount, promotionDiscount, memberShipDiscount, payment);
    }

    private int calculateTotalAmount() {
        return orderProducts.stream()
                .mapToInt(OrderProduct::getTotal)
                .sum();
    }

    private int calculatePromotionDiscount() {
        return orderProducts.stream()
                .mapToInt(OrderProduct::getPromotionDiscount)
                .sum();
    }

    private int calculateMembershipDiscount(int totalAmount, int promotionDiscount) {
        if (!hasMembershipDiscount) {
            return 0;
        }
        int calculatedDiscount = (int) ((totalAmount - promotionDiscount) * 0.3);
        return Math.min(calculatedDiscount, 8000);
    }

    private int calculateTotalCount() {
        return orderProducts.stream()
                .mapToInt(OrderProduct::getCount)
                .sum();
    }

    private List<OrderGift> getOrderGifts() {
        //product 돌면서 promotionQuantity랑 이름 추출
        return orderProducts.stream()
                .filter(OrderProduct::hasGift)
                .map(orderProduct -> new OrderGift(orderProduct.getName(), orderProduct.getGiftCount()))
                .toList();
    }
}
