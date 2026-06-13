package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class LikeCounterRaceTest {

    @Test
    @DisplayName("보호 없는 카운터는 최종값이 기대값을 넘지 않는다 (불변식)")
    void countNeverExceedsExpected() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            int threads = 50;
            int perThread = 1000;
            int expected = threads * perThread;

            // 갱신 손실은 "더해지지 않는" 방향으로만 일어나므로, 최종값은 절대 기대값을 넘지 못한다.
            // 이건 어떤 스케줄에서도 항상 참이라 결정적으로 Green 이다.
            int actual = LikeCounterRace.runWorkload(threads, perThread);
            assertTrue(actual <= expected,
                    "보호 없는 카운터의 최종값(" + actual + ")이 기대값(" + expected + ")을 넘으면 안 된다");
        });
    }

    @Test
    @DisplayName("워크로드를 여러 번 돌리면 적어도 한 번은 좋아요 손실이 관찰된다")
    void lostUpdateIsObservedAcrossTrials() {
        assertTimeoutPreemptively(Duration.ofSeconds(20), () -> {
            int threads = 50;
            int perThread = 1000;
            int expected = threads * perThread;

            // 단일코어 CI 에서 한 번 정도는 우연히 정확히 맞을 수 있어 flaky 하다.
            // 그래서 여러 번 반복해서 "최소 한 번이라도 손실(actual < expected)이 보이면" 통과로 본다.
            // "항상 손실" 은 절대 단정하지 않는다.
            boolean lossObserved = false;
            for (int trial = 0; trial < 5 && !lossObserved; trial++) {
                int actual = LikeCounterRace.runWorkload(threads, perThread);
                if (actual < expected) {
                    lossObserved = true;
                }
            }

            assertTrue(lossObserved,
                    "5번의 워크로드 중 한 번도 갱신 손실이 관찰되지 않았다 (경쟁 상태 재현 실패)");
        });
    }
}
