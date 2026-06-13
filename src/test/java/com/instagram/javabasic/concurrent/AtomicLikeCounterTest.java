package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AtomicLikeCounterTest {

    @Test
    @DisplayName("AtomicInteger 로 세면 100스레드 × 1000번을 동시에 눌러도 정확히 100,000 이다")
    void atomicCounterNeverLosesIncrements() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // Atomic 은 증가를 한 동작으로 보장하므로, 어떤 환경에서도 항상 정확히 맞는다.
            int result = AtomicLikeCounter.runWorkload(100, 1000);
            assertEquals(100_000, result,
                    "Atomic 카운터는 갱신을 잃지 않아 정확히 100,000 이어야 한다");
        });
    }
}
