package store.controller;

import store.service.OrderService;
import store.service.ProductService;
import store.view.InputView;
import store.view.OutputView;

public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;
    private final InputView inputView;
    private final OutputView outputView;

    public OrderController(OrderService orderService, ProductService productService, InputView inputView, OutputView outputView) {
        this.orderService = orderService;
        this.productService = productService;
        this.inputView = inputView;
        this.outputView = outputView;
    }



}
