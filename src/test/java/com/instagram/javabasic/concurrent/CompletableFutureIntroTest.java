package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CompletableFutureIntroTest {

    @Test
    @DisplayName("supplyAsync 의 결과를 join() 으로 받아 인사 문자열을 돌려준다")
    void supplyGreetingReturnsResult() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals("안녕하세요, jaehoon님!", CompletableFutureIntro.supplyGreeting("jaehoon"));
        });
    }

    @Test
    @DisplayName("supplyAsync 안에서 계산한 값(좋아요 두 배)을 받아온다")
    void doubleLikesReturnsResult() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals(42, CompletableFutureIntro.doubleLikes(21));
        });
    }

    @Test
    @DisplayName("runAsync 는 결과 없이 부수효과(카운터 1 증가)만 낸다")
    void runAsyncRunsSideEffect() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals(1, CompletableFutureIntro.runAsyncThenCount());
        });
    }
}
