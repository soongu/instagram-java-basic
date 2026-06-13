package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BatchBarrierTest {

    @Test
    @DisplayName("일꾼 3명이 2단계 배치를 발맞춰 끝내면 완료된 단계 수가 정확히 2다")
    void allWorkersAdvanceTogetherEachRound() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 전원이 한 단계 장벽을 통과할 때마다 장벽 동작이 1번 실행되므로, 2단계면 정확히 2다.
            assertEquals(2, BatchBarrier.runBatches(3, 2),
                    "2단계를 모두 발맞춰 통과하면 완료 단계 수가 2여야 한다");
        });
    }

    @Test
    @DisplayName("순환 장벽은 재사용되어 여러 단계를 반복해도 단계 수가 맞는다")
    void barrierIsReusableAcrossManyRounds() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals(5, BatchBarrier.runBatches(4, 5));
        });
    }
}
