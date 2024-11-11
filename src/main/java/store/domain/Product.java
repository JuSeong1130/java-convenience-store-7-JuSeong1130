package store.domain;

public class Product {

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
}