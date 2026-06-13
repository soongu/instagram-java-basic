package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PoolTypeDemoTest {

    @Test
    @DisplayName("고정 풀(크기 2)은 일감 6개를 모두 처리하되 일꾼 수가 2를 절대 넘지 않는다")
    void fixedPoolNeverExceedsItsSize() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            PoolTypeDemo.FixedResult result = PoolTypeDemo.runOnFixed(2, 6);
            assertEquals(6, result.completed(), "일감 6개는 모두 처리되어야 한다");
            // 고정 풀은 정의상 크기를 넘는 일꾼을 만들 수 없으므로 항상 참인 결정적 단언.
            assertTrue(result.distinctThreads() <= 2,
                    "고정 풀(2)이 사용한 서로 다른 스레드 수는 2 이하여야 한다");
            assertTrue(result.distinctThreads() >= 1, "적어도 한 스레드는 일했어야 한다");
        });
    }

    @Test
    @DisplayName("탄력 풀은 일감 6개를 모두 처리한다")
    void cachedPoolRunsAllTasks() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals(6, PoolTypeDemo.runOnCached(6));
        });
    }
}
