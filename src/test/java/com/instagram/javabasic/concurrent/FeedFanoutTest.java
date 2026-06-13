package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedFanoutTest {

    @Test
    @DisplayName("allOf 로 전부 끝나길 기다린 뒤 각 좋아요 수를 합산한다")
    void totalLikesWaitsForAll() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 1*10 + 2*10 + 3*10 = 10 + 20 + 30 = 60
            assertEquals(60, FeedFanout.totalLikesOfFeed(new int[] {1, 2, 3}));
        });
    }

    @Test
    @DisplayName("anyOf 의 결과는 어느 게 먼저 끝날지 모르므로 멤버십으로만 단언한다")
    void firstReadyReturnsOneOfThem() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 승자는 스케줄링에 따라 달라지지만, 항상 {1,2,3} 중 하나 = 비-flaky
            assertTrue(Set.of(1, 2, 3).contains(FeedFanout.firstReady(new int[] {1, 2, 3})));
        });
    }
}
