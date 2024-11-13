package store.view.dto;

import store.domain.OrderProduct;

public record ResponseOrderProduct (String name, int quantity, int price){
    public static ResponseOrderProduct from(OrderProduct orderProduct) {
        return new ResponseOrderProduct(orderProduct.getName(), orderProduct.getCount(), orderProduct.getTotal());
    }
}
