package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FeedTaskExecutorTest {

    @Test
    @DisplayName("명시적 shutdown 방식으로 이미지 12장을 모두 로딩한다")
    void explicitShutdownLoadsAll() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals(12, FeedTaskExecutor.loadImagesExplicit(12));
        });
    }

    @Test
    @DisplayName("try-with-resources 방식은 블록을 벗어날 때 모든 일감이 끝나 12장이 로딩된다")
    void tryWithResourcesWaitsForAll() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // close() 가 모든 일감 완료를 기다려주므로 결과는 항상 12다.
            assertEquals(12, FeedTaskExecutor.loadImages(12),
                    "try-with-resources 가 close() 에서 전부 기다리므로 12여야 한다");
        });
    }
}
