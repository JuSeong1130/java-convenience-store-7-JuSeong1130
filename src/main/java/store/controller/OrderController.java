package store.controller;

import store.controller.dto.OrderStateDTO;
import store.controller.dto.StateContextDTO;
import store.domain.Command;
import store.service.OrderService;
import store.service.ProductService;
import store.view.InputView;
import store.view.OutputView;
import store.view.dto.RequestOrder;
import store.view.dto.ResponseOrder;
import store.view.dto.ResponseProducts;

import java.util.function.Supplier;

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

    public void createOrder() {
        Command retryCommand;
        do {
            printProductInfo();
            OrderStateDTO orderStateDTO = getOrderStateDTO();
            checkOrderState(orderStateDTO);
            Command command = inputCommand(inputView::askMemberShipDiscount);
            orderService.determinedMembership(command);
            getReceipt();
            retryCommand = inputCommand(inputView::askAdditionalPurchase);
        } while (retryCommand.isYes());
    }

    private void checkOrderState(OrderStateDTO orderState) {
        if(orderState.state().equals("PENDING")) {
            for (StateContextDTO stateContext : orderState.stateContexts()) {
                Command command = inputCommand(() -> inputView.askReviewPending(stateContext));
                orderService.solvePending(command, stateContext.name());
            }
        }
    }

    private void printProductInfo() {
        ResponseProducts products = productService.findAll();
        outputView.printProducts(products);
    }

    private OrderStateDTO getOrderStateDTO() {
        try {
            RequestOrder requestOrder = inputView.askItem();
            return orderService.createOrder(requestOrder);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return getOrderStateDTO();
        }
    }

    private Command inputCommand(Supplier<String> supplier) {
        try {
            String input = supplier.get();
            return Command.find(input);
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            return inputCommand(supplier);
        }
    }

    public void getReceipt() {
        ResponseOrder orderReceipt = orderService.getOrderReceipt();
        outputView.printOrder(orderReceipt);
    }
}
