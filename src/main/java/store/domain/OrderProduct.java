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

    public void solvePending(Command command) {
        if(command.isYes()) {
            handleYesCommand();
        }
        if(command.isNo() && orderProductState == OrderProductState.GIFT) {
            product.decreaseCommonQuantity(pendingQuantity);
            this.commonQuantity += pendingQuantity;
        }
        this.orderProductState = OrderProductState.SOLVE;
        this.pendingQuantity = 0;
    }

    private void handleYesCommand() {
        if (orderProductState == OrderProductState.GIFT) {
            applyGiftQuantity();
        } else if (orderProductState == OrderProductState.EXCLUSION) {
            applyExclusionQuantity();
        }
    }

    private void applyGiftQuantity() {
        product.decreasePromotionQuantity(pendingQuantity + giftQuantity);
        this.promotionQuantity += pendingQuantity + giftQuantity;
        resetQuantities();
    }

    private void resetQuantities() {
        this.pendingQuantity = 0;
        this.giftQuantity = 0;
    }

    private void applyExclusionQuantity() {
        int remainingQuantity = pendingQuantity;
        remainingQuantity -= product.decreasePromotionQuantity(pendingQuantity);
        product.decreaseCommonQuantity(remainingQuantity);
        this.commonQuantity += pendingQuantity;
        this.pendingQuantity = 0;
    }

    public boolean hasGift() {
        return getGiftCount() > 0;
    }

    public int getGiftCount() {
        return product.calculateGiftCount(promotionQuantity);
    }

    public int getTotal() {
        return product.getPrice() * (promotionQuantity + commonQuantity);
    }

    public int getPromotionAmount() {
        return product.getPrice() * promotionQuantity;
    }

    public int getPromotionDiscount() {
        return getGiftCount() * product.getPrice();
    }

    public int getCount() {
        return promotionQuantity + commonQuantity;
    }
}