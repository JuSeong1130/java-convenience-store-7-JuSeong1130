package store.config;

import store.controller.OrderController;
import store.domain.Product;
import store.repository.OrderRepository;
import store.repository.ProductRepository;
import store.service.OrderService;
import store.service.ProductService;
import store.util.ProductLoader;
import store.view.InputView;
import store.view.OutputView;

import java.util.List;

public class AppConfig {

    private static final ProductRepository productRepository = new ProductRepository(getProduct());

    private static ProductService getProductService() {
        return new ProductService(getProductRepository());
    }

    private static ProductRepository getProductRepository() {
        return productRepository;
    }

    private static List<Product> getProduct() {
        return ProductLoader.loadProducts();
    }

    public static OrderController getOrderController() {
        return new OrderController(getOrderService(), getProductService(), getInputView(), getOutputView());
    }

    private static OrderService getOrderService() {
        return new OrderService(getOderRepository(), getProductRepository());
    }

    private static OrderRepository getOderRepository() {
        return new OrderRepository();
    }

    private static InputView getInputView() {
        return new InputView();
    }

    private static OutputView getOutputView() {
        return new OutputView();
    }

}
