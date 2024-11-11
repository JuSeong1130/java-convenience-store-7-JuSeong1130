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
        List<Product> products = new ArrayList<>();
        for (Map.Entry<String, List<String[]>> entry : groupedLines.entrySet()) {
            String name = entry.getKey();
            int price = 0;
            int promotionQuantity = 0;
            int commonQuantity = 0;
            Promotion promotion = new Promotion("",Integer.MAX_VALUE,0, LocalDateTime.MIN, LocalDateTime.MIN);

            for (String[] values : entry.getValue()) {
                price = Integer.parseInt(values[1]);
                int quantity = Integer.parseInt(values[2]);
                String promotionName = values[3];

                if ("null".equals(promotionName)) {
                    commonQuantity += quantity;
                } else {
                    promotionQuantity += quantity;
                    promotion = promotions.get(promotionName);
                }
            }
            products.add(new Product(name, price, promotionQuantity, commonQuantity, promotion));
        }

        return products;
    }

}
