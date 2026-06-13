package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VirtualThreadPitfallsTest {

    @Test
    @DisplayName("가상 스레드는 작업마다 새로 만들어진다 — 50개 작업은 서로 다른 50개 스레드를 쓴다")
    void noReuseSoNoPooling() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            // 재사용이 있다면 스레드 수 < 작업 수가 되겠지만, 가상 스레드는 매번 새로 만들어진다.
            assertEquals(50, VirtualThreadPitfalls.distinctThreadCount(50));
        });
    }

    @Test
    @DisplayName("세마포어로 동시 실행을 4로 제한하면 동시 실행 피크가 4를 넘지 않는다")
    void semaphoreCapsConcurrency() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            int peak = VirtualThreadPitfalls.peakConcurrency(100, 4);
            // 세마포어 정원이 4이므로, 동시에 일한 최대 개수는 절대 4를 넘을 수 없다.
            assertTrue(peak <= 4, "동시 실행 피크는 4 이하여야 하는데 " + peak + " 였다");
            assertTrue(peak >= 1, "적어도 1개는 동시에 일했어야 한다");
        });
    }
}
