package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotificationSchedulerTest {

    @Test
    @DisplayName("일회성 예약은 지연 시간 뒤 한 번 실행되어 결과를 돌려준다")
    void scheduleOnceReturnsResult() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals("새 알림 1건", NotificationScheduler.checkOnce(50));
        });
    }

    @Test
    @DisplayName("주기 예약은 목표 횟수만큼은 반드시 실행된 뒤 멈춘다")
    void scheduleAtFixedRateRunsAtLeastTarget() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 목표 4번이 채워질 때까지 기다렸다 멈추므로 실제 실행 횟수는 4 이상이다 (결정적 하한).
            int runs = NotificationScheduler.runPeriodically(4, 20);
            assertTrue(runs >= 4, "주기 실행이 적어도 4번은 일어났어야 한다 (실제: " + runs + ")");
        });
    }
}
