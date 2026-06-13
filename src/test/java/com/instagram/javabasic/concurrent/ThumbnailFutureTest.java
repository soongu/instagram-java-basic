package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThumbnailFutureTest {

    @Test
    @DisplayName("Callable 의 결과를 Future.get() 으로 받아 썸네일 너비(절반)를 돌려준다")
    void singleFutureReturnsResult() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals(40, ThumbnailFuture.generateThumbnail(80));
        });
    }

    @Test
    @DisplayName("여러 Future 를 모아 하나씩 get() 으로 거두면 절반 너비의 합이 나온다")
    void multipleFuturesSumUp() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 100/2 + 200/2 + 300/2 = 50 + 100 + 150 = 300
            assertEquals(300, ThumbnailFuture.totalThumbnailWidth(new int[] {100, 200, 300}));
        });
    }
}
