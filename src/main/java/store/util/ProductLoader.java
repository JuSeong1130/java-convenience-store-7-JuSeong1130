package store.util;

import store.domain.Product;
import store.domain.Promotion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class ProductLoader {

    private static final String FILE_PATH = "./src/main/resources/products.md";

    public static  List<Product> loadProducts() {
        Map<String, Promotion> promotions = PromotionLoader.loadPromotion();
        Map<String, List<String[]>> groupedLines = getGroups();
        return getProducts(groupedLines, promotions);
    }

    private static List<Product> getProducts(Map<String, List<String[]>> groupedLines, Map<String, Promotion> promotions) {
        List<Product> products = new ArrayList<>();

        for (Map.Entry<String, List<String[]>> entry : groupedLines.entrySet()) {
            String name = entry.getKey();
            Product product = createProduct(name, entry.getValue(), promotions);
            products.add(product);
        }

        return products;
    }

    private static Product createProduct(String name, List<String[]> valuesList, Map<String, Promotion> promotions) {
        int price = 0;
        int promotionQuantity = 0;
        int commonQuantity = 0;
        Promotion promotion = createDefaultPromotion();

        for (String[] values : valuesList) {
            price = Integer.parseInt(values[1]);
            int quantity = Integer.parseInt(values[2]);
            String promotionName = values[3];

            if ("null".equals(promotionName)) {
                commonQuantity += quantity;
            } else {
                promotionQuantity += quantity;
                promotion = promotions.getOrDefault(promotionName, promotions.get(promotionName));
            }
        }

        return new Product(name, price, promotionQuantity, commonQuantity, promotion);
    }

    private static Promotion createDefaultPromotion() {
        return new Promotion("", Integer.MAX_VALUE, 0, LocalDateTime.MIN, LocalDateTime.MIN);
    }

    private static Map<String, List<String[]>> getGroups() {
        Map<String, List<String[]>> groupedLines = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            br.readLine(); // Skip header
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                groupedLines.computeIfAbsent(name, k -> new ArrayList<>()).add(values);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return groupedLines;
    }

}
