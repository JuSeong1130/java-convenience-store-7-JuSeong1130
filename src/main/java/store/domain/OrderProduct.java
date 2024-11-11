package store.domain;

public class OrderProduct {

    private final Product product;
    private int promotionQuantity;
    private int commonQuantity;
    private int pendingQuantity;
    private int giftQuantity;
    private OrderProductState orderProductState;

    public OrderProduct(Product product, int promotionQuantity, int commonQuantity, int pendingQuantity, int giftQuantity, OrderProductState orderProductState) {
        this.product = product;
        this.promotionQuantity = promotionQuantity;
        this.commonQuantity = commonQuantity;
        this.pendingQuantity = pendingQuantity;
        this.giftQuantity = giftQuantity;
        this.orderProductState = orderProductState;
    }

    public OrderProductState getState() {
        return orderProductState;
    }

    public String getName() {
        return product.getName();
    }
}