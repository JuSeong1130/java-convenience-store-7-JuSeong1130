package store.view.dto;

import java.util.List;

public record ResponseOrder(List<ResponseOrderProduct> orderProducts, List<ResponseGift> gifts, ResponseReceipt receipt) {
}
