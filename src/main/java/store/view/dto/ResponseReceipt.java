package store.view.dto;

public record ResponseReceipt(int totalCount, int totalAmount, int promotionDiscount, int membershipDiscount, int payableAmount) {
}
