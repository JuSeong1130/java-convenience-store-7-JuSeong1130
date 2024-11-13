package store;

import store.config.AppConfig;
import store.controller.OrderController;

public class Application {

    public static void main(String[] args) {
        // TODO: 프로그램 구현
        OrderController orderController = AppConfig.getOrderController();
        orderController.createOrder();
    }

}
