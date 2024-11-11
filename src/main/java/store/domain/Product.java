package store.domain;

import java.time.LocalDateTime;

public class Product {

    public static final String EXCEED_QUANTITY = "재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.";
    private final String name;
    private final int price;
    private int promotionQuantity;
    private int commonQuantity;
    private final Promotion promotion;

    public Product(String name, int price, int promotionQuantity, int commonQuantity, Promotion promotion) {
        this.name = name;
        this.price = price;
        this.promotionQuantity = promotionQuantity;
        this.commonQuantity = commonQuantity;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getPromotionQuantity() {
        return promotionQuantity;
    }

    public int getCommonQuantity() {
        return commonQuantity;
    }

    public String getPromotionName() {
        return promotion.getName();
    }

    public ApplyResult applyResult(int quantity, LocalDateTime now) {
        if (canApplyPromotion(now)) {
            return applyPromotion(quantity);
        }

        return applyCommonProduct(quantity);
    }

    private boolean canApplyPromotion(LocalDateTime now) {
        return promotion.isInRange(now);
    }

    private ApplyResult applyPromotion(int quantity) {
        validateStockAvailability(quantity);
        int promotionTotalCount = promotion.getBuy() + promotion.getGet();
        int remainingQuantity = quantity;
        int applyPromotionQuantity = 0;

        if (!isEnoughPromotionStock(promotionTotalCount)) {
            return new ApplyResult(0, 0, remainingQuantity, 0);
        }

        while (remainingQuantity >= promotionTotalCount) {
            remainingQuantity -= promotionTotalCount;
            applyPromotionQuantity += promotionTotalCount;
            this.promotionQuantity -= promotionTotalCount;
        }

        if (remainingQuantity == 0) {
            return new ApplyResult(applyPromotionQuantity, 0, 0, 0);
        }

        return handleRemainingQuantity(remainingQuantity, promotion.getBuy(), promotion.getGet(), applyPromotionQuantity);
    }

    private void validateStockAvailability(int quantity) {
        if (this.promotionQuantity + this.commonQuantity < quantity) {
            throw new IllegalArgumentException(EXCEED_QUANTITY);
        }
    }

    private boolean isEnoughPromotionStock(int promotionTotalCount) {
        return this.promotionQuantity >= promotionTotalCount;
    }

    private ApplyResult handleRemainingQuantity(int remainingQuantity, int buy, int get, int applyPromotionQuantity) {
        if (remainingQuantity == buy && this.promotionQuantity >= (buy + get)) {
            return new ApplyResult(applyPromotionQuantity, 0, buy, get);
        }
        return new ApplyResult(applyPromotionQuantity, 0, remainingQuantity, 0);
    }

    private ApplyResult applyCommonProduct(int quantity) {
        if (this.commonQuantity < quantity) {
            throw new IllegalArgumentException(EXCEED_QUANTITY);
        }
        this.commonQuantity -= quantity;
        return new ApplyResult(0, quantity, 0, 0);
    }

    public int decreasePromotionQuantity(int quantity) {
        int minQuantity = Math.min(this.promotionQuantity, quantity);
        this.promotionQuantity -= minQuantity;
        return minQuantity;
    }

    public int decreaseCommonQuantity(int quantity) {
        int minQuantity = Math.min(this.commonQuantity, quantity);
        this.commonQuantity -= minQuantity;
        return minQuantity;
    }

}