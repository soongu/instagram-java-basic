package com.instagram.javabasic.exceptionbasic.solution.day23;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LikeCountValidatorTest {

    @Test
    @DisplayName("0 이상이면 그 값을 그대로 돌려준다")
    void returnsValueWhenNonNegative() {
        LikeCountValidator validator = new LikeCountValidator();

        assertEquals(100, validator.validateLikeCount(100));
    }

    @Test
    @DisplayName("0 도 정상 값이라 그대로 돌려준다")
    void returnsZeroWhenZero() {
        LikeCountValidator validator = new LikeCountValidator();

        assertEquals(0, validator.validateLikeCount(0));
    }

    @Test
    @DisplayName("음수면 InvalidLikeCountException 을 던진다")
    void throwsCustomWhenNegative() {
        LikeCountValidator validator = new LikeCountValidator();

        assertThrows(InvalidLikeCountException.class,
                () -> validator.validateLikeCount(-5));
    }

    @Test
    @DisplayName("던진 예외 메시지에 잘못된 값이 담긴다")
    void messageContainsValue() {
        LikeCountValidator validator = new LikeCountValidator();

        InvalidLikeCountException thrown = assertThrows(
                InvalidLikeCountException.class,
                () -> validator.validateLikeCount(-5));

        assertEquals("좋아요 수는 0 이상이어야 해요: -5", thrown.getMessage());
    }

    @Test
    @DisplayName("message 만 받은 예외는 cause 가 없다")
    void messageOnlyConstructorHasNoCause() {
        InvalidLikeCountException e = new InvalidLikeCountException("잘못된 값");

        assertEquals("잘못된 값", e.getMessage());
    }

    @Test
    @DisplayName("message + cause 생성자는 원인을 보존한다")
    void causeConstructorPreservesCause() {
        IllegalArgumentException origin = new IllegalArgumentException("원래 사고");

        InvalidLikeCountException e = new InvalidLikeCountException("재포장", origin);

        assertSame(origin, e.getCause());
    }
}
