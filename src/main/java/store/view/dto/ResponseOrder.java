package store.view.dto;

import store.domain.OrderGift;
import store.domain.OrderPayment;
import store.domain.OrderProduct;
import store.domain.OrderReceipt;

import java.util.ArrayList;
import java.util.List;

public record ResponseOrder(List<ResponseOrderProduct> orderProducts, List<ResponseGift> gifts, ResponseReceipt receipt) {
    public static ResponseOrder from(OrderReceipt orderReceipt) {
        List<ResponseOrderProduct> responseOrderProducts = new ArrayList<>();
        List<OrderProduct> orderProducts = orderReceipt.orderProducts();
        orderProducts.forEach(orderProduct -> responseOrderProducts.add(ResponseOrderProduct.from(orderProduct)));
        List<ResponseGift> responseGifts = new ArrayList<>();
        List<OrderGift> orderGifts = orderReceipt.orderGifts();
        orderGifts.forEach(orderGift -> responseGifts.add(ResponseGift.from(orderGift)));
        OrderPayment orderPayment = orderReceipt.orderPayment();
        ResponseReceipt responseReceipt = new ResponseReceipt(orderPayment.totalCount(), orderPayment.totalPrice(), orderPayment.promotionDiscountPrice(), orderPayment.membershipDiscountPrice(), orderPayment.payPrice());
        return new ResponseOrder(responseOrderProducts, responseGifts, responseReceipt);
    }
}
