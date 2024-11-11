package store.domain;

public record OrderPayment(int totalCount, int totalPrice, int promotionDiscountPrice, int membershipDiscountPrice, int payPrice) {
}
