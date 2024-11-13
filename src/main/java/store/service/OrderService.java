package store.service;

import camp.nextstep.edu.missionutils.DateTimes;
import store.controller.dto.OrderStateDTO;
import store.controller.dto.StateContextDTO;
import store.domain.*;
import store.repository.OrderRepository;
import store.repository.ProductRepository;
import store.view.dto.RequestOrder;
import store.view.dto.RequestOrderProduct;
import store.view.dto.ResponseOrder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public OrderStateDTO createOrder(RequestOrder requestOrder) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        List<StateContextDTO> stateContexts = new ArrayList<>();
        OrderState orderState = OrderState.SUCCESS;
        LocalDateTime now = DateTimes.now();
        for (RequestOrderProduct requestOrderProduct : requestOrder.orderProducts()) {
            Product product = productRepository.findByName(requestOrderProduct.name());
            ApplyResult applyResult = product.applyResult(requestOrderProduct.quantity(), now);
            OrderProduct orderProduct = createOrderProduct(product, applyResult, now);
            OrderProductState orderProductState = orderProduct.getState();
            orderState = calcaulteOrderState(orderProductState);
            orderProducts.add(orderProduct);
            stateContexts.add(new StateContextDTO(orderProductState.getState(), product.getName(), getQuantity(applyResult, orderProductState)));
        }
        Order order = new Order(orderProducts, orderState);
        orderRepository.save(order);
        return new OrderStateDTO(orderState.getState(), stateContexts);
    }

    private OrderProduct createOrderProduct(Product product, ApplyResult applyResult,  LocalDateTime now) {
        OrderProductState orderProductState = getOrderProductState(applyResult);

        return new OrderProduct(
                product,
                applyResult.promotionQuantity(),
                applyResult.commonQuantity(),
                applyResult.pendingQuantity(),
                applyResult.giftQuantity(),
                orderProductState
        );
    }

    private OrderProductState getOrderProductState(ApplyResult applyResult) {
        if(applyResult.pendingQuantity() == 0) {
            return OrderProductState.SOLVE;
        }
        if(applyResult.giftQuantity() == 0) {
            return OrderProductState.EXCLUSION;
        }
        return OrderProductState.GIFT;
    }

    private int getQuantity(ApplyResult applyResult, OrderProductState orderProductState) {
        if(orderProductState == OrderProductState.GIFT) {
            return applyResult.giftQuantity();
        }
        return applyResult.pendingQuantity();
    }

    private OrderState calcaulteOrderState(OrderProductState orderProductState) {
        if(orderProductState == OrderProductState.SOLVE) {
            return OrderState.SUCCESS;
        }
        return OrderState.PENDING;
    }

    public void solvePending(Command command, String name) {
        Optional<Order> order = orderRepository.find();
        if (order.isEmpty()) {
            throw new IllegalStateException("Order not found");
        }
        order.get().solvePending(name, command);
    }

    public void determinedMembership(Command command) {
        Order order = orderRepository.find().get();
        if (command.isYes()) {
            order.activeMembership();
        }
    }

    public ResponseOrder getOrderReceipt() {
        Order order = orderRepository.find().get();
        OrderReceipt receipt = order.getReceipt();
        return ResponseOrder.from(receipt);
    }
}
