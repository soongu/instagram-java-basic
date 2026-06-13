package com.instagram.javabasic.concurrent.solution.day37;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThumbnailRateLimiterTest {

    @Test
    @DisplayName("동시 4장으로 제한하면 이미지 100장을 처리해도 동시 실행 피크가 4를 넘지 않는다")
    void capsConcurrencyAtFour() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            int peak = ThumbnailRateLimiter.generateWithLimit(100, 4);
            // 세마포어 정원이 4이므로, 동시에 일한 최대 개수는 절대 4를 넘을 수 없다.
            assertTrue(peak <= 4, "동시 실행 피크는 4 이하여야 하는데 " + peak + " 였다");
            assertTrue(peak >= 1, "적어도 1장은 동시에 처리됐어야 한다");
        });
    }

    @Test
    @DisplayName("동시 1장으로 제한하면 한 번에 한 장씩만 처리된다")
    void serialWhenLimitIsOne() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            int peak = ThumbnailRateLimiter.generateWithLimit(20, 1);
            assertTrue(peak <= 1, "정원이 1이면 피크도 1 이하여야 하는데 " + peak + " 였다");
        });
    }
}
