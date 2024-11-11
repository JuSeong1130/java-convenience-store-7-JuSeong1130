package store.domain;

import java.util.List;

public record OrderReceipt(List<OrderProduct> orderProducts, List<OrderGift> orderGifts, OrderPayment orderPayment) {

}
