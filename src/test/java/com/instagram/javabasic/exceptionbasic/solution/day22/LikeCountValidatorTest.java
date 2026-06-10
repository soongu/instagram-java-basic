package com.instagram.javabasic.exceptionbasic.solution.day22;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LikeCountValidatorTest {

    @Test
    @DisplayName("0 이상이면 그 값을 그대로 돌려준다")
    void returnsValueWhenNonNegative() {
        LikeCountValidator validator = new LikeCountValidator();

        int result = validator.validateLikeCount(100);

        assertEquals(100, result);
    }

    @Test
    @DisplayName("0 도 정상 값이라 그대로 돌려준다")
    void returnsZeroWhenZero() {
        LikeCountValidator validator = new LikeCountValidator();

        int result = validator.validateLikeCount(0);

        assertEquals(0, result);
    }

    @Test
    @DisplayName("음수가 들어오면 IllegalArgumentException 을 던진다")
    void throwsWhenNegative() {
        LikeCountValidator validator = new LikeCountValidator();

        assertThrows(IllegalArgumentException.class,
                () -> validator.validateLikeCount(-5));
    }
}
