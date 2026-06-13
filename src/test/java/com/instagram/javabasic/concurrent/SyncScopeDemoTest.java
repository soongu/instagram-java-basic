package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SyncScopeDemoTest {

    @Test
    @DisplayName("synchronized 메서드 전체 잠금은 정확한 결과를 낸다")
    void wholeMethodLockIsExact() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            int threads = 50;
            int perThread = 1000;
            int expected = threads * perThread;

            int actual = SyncScopeDemo.runWorkload(false, threads, perThread);
            assertEquals(expected, actual, "메서드 전체 잠금은 정확히 " + expected + " 이어야 한다");
        });
    }

    @Test
    @DisplayName("synchronized 블록으로 좁게 잠가도 정확한 결과를 낸다")
    void narrowBlockLockIsExact() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            int threads = 50;
            int perThread = 1000;
            int expected = threads * perThread;

            // 임계 구역만 좁게 잠가도 공유 값 보호는 똑같이 되므로 결과가 정확하다 (결정적).
            int actual = SyncScopeDemo.runWorkload(true, threads, perThread);
            assertEquals(expected, actual, "블록 잠금도 정확히 " + expected + " 이어야 한다");
        });
    }
}
