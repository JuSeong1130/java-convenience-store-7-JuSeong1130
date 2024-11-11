package store.view.dto;

import java.util.List;

public record RequestOrder(List<RequestOrderProduct> orderProducts) {
}
