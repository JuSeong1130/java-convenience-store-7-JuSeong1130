package store.repository;

import store.domain.Order;

import java.util.Optional;

public class OrderRepository {

    private Optional<Order> order;

    public OrderRepository() {
        this.order = Optional.empty();
    }

    public void save(Order order) {
        this.order = Optional.of(order);
    }

    public Optional<Order> find() {
        return order;
    }


}
