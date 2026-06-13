package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ThreadScaleDemoTest {

    @Test
    @DisplayName("가상 스레드 1만 개를 동시에 만들어도 전부 완료된다")
    void tenThousandVirtualThreadsAllComplete() {
        // 1만 개가 각자 50ms 쉬어도, 가상 스레드는 막힐 때 OS 스레드를 놓아주므로 금방 끝난다.
        // 타이밍이 아니라 "전부 완료됐는가(개수)" 로 단언해 흔들리지 않게 한다.
        assertTimeoutPreemptively(Duration.ofSeconds(30), () -> {
            assertEquals(10_000, ThreadScaleDemo.runManyVirtualThreads(10_000));
        });
    }

    @Test
    @DisplayName("작은 개수(100개)도 빠짐없이 완료된다")
    void smallBatchAlsoCompletes() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            assertEquals(100, ThreadScaleDemo.runManyVirtualThreads(100));
        });
    }
}
