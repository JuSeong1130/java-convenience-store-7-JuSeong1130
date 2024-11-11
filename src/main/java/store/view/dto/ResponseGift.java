package store.view.dto;

import store.domain.OrderGift;

public record ResponseGift (String name, int quantity){
    public static ResponseGift from(OrderGift orderGift) {
        return new ResponseGift (orderGift.name(), orderGift.quantity());
    }
}
