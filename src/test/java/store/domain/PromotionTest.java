package store.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PromotionTest {

    private Promotion promotion;

    @BeforeEach
    void setUp() {
        promotion = new Promotion("테스트",2, 1, LocalDateTime.parse("2024-01-01T00:00:00"), LocalDateTime.parse("2024-12-31T00:00:00"));
    }

    @Test
    @DisplayName("Promotion 기간에 들어간다면 True")
    void isInRange_True() {
        //give
        LocalDateTime date = LocalDateTime.parse("2024-05-01T00:00:00");
        //when //then
        Assertions.assertThat(promotion.isInRange(date)).isTrue();
    }

    @Test
    @DisplayName("Promotion 기간에 들어간다면 True")
    void isInRange_False() {
        //give
        LocalDateTime date = LocalDateTime.parse("2023-05-01T00:00:00");
        //when //then
        Assertions.assertThat(promotion.isInRange(date)).isFalse();
    }


}