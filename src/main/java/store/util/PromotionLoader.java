package store.util;

import store.domain.Promotion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class PromotionLoader {

    private static final String FILE_PATH = "./src/main/resources/promotions.md";

    public static Map<String, Promotion> loadPromotion() {
        Map<String, Promotion> promotions = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            br.readLine(); // 첫번쨰 헤더 생략
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String name = values[0];
                int buy = Integer.parseInt(values[1]);
                int get = Integer.parseInt(values[2]);
                LocalDateTime startDate = LocalDateTime.parse(values[3]+ "T00:00:00");
                LocalDateTime endDate = LocalDateTime.parse(values[4] + "T00:00:00");
                Promotion promotion = new Promotion(name, buy, get, startDate, endDate);
                promotions.put(name, promotion);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return promotions;
    }

}
