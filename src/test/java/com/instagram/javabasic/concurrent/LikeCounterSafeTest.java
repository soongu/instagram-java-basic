package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LikeCounterSafeTest {

    @Test
    @DisplayName("synchronized 카운터는 멀티스레드에서도 정확히 기대값을 낸다")
    void synchronizedCounterIsExact() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            int threads = 50;
            int perThread = 1000;
            int expected = threads * perThread;

            // synchronized 덕분에 갱신 손실이 없어 결과가 항상 정확하다 (결정적 Green).
            int actual = LikeCounterSafe.runWorkload(threads, perThread);
            assertEquals(expected, actual,
                    "synchronized 카운터는 갱신 손실 없이 정확히 " + expected + " 이어야 한다");
        });
    }

    @Test
    @DisplayName("여러 번 돌려도 synchronized 카운터는 매번 정확하다")
    void synchronizedCounterIsExactAcrossTrials() {
        assertTimeoutPreemptively(Duration.ofSeconds(15), () -> {
            int threads = 50;
            int perThread = 1000;
            int expected = threads * perThread;

            // 경쟁 상태 데모와 달리, 몇 번을 돌려도 늘 정확하다는 점이 핵심이다.
            for (int trial = 0; trial < 3; trial++) {
                int actual = LikeCounterSafe.runWorkload(threads, perThread);
                assertEquals(expected, actual, (trial + 1) + "번째 시도에서도 정확해야 한다");
            }
        });
    }
}
