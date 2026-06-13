package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ExecutorIntroTest {

    @Test
    @DisplayName("일꾼 3명 풀에 일감 10개를 던지면 10개가 모두 처리된다")
    void poolRunsAllTasks() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // shutdown 후 awaitTermination 으로 전부 끝났음을 보장하므로 항상 10이다.
            assertEquals(10, ExecutorIntro.runTasks(10),
                    "풀이 던진 일감을 모두 처리해 끝낸 일감 수가 10이어야 한다");
        });
    }

    @Test
    @DisplayName("일감 0개면 끝낸 일감도 0개다")
    void poolHandlesEmptyWorkload() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals(0, ExecutorIntro.runTasks(0));
        });
    }
}
