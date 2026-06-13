package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VirtualFeedLoaderTest {

    @Test
    @DisplayName("가상 스레드 executor 로 게시물 100개를 빠짐없이 불러온다")
    void loadsAllPosts() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // try-with-resources 의 close() 가 모든 작업 완료를 기다리므로 항상 100이다.
            assertEquals(100, VirtualFeedLoader.loadFeed(100));
        });
    }

    @Test
    @DisplayName("executor 가 띄운 작업은 모두 가상 스레드 위에서 실행된다")
    void everyTaskRunsOnVirtualThread() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals(100, VirtualFeedLoader.virtualTaskCount(100),
                    "newVirtualThreadPerTaskExecutor 의 모든 작업은 가상 스레드여야 한다");
        });
    }
}
