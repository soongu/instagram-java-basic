package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AsyncPipelineTest {

    @Test
    @DisplayName("thenApply 를 이어 붙여 문자열을 대문자로 바꾸고 길이를 돌려준다")
    void profileNameLengthChainsApply() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // "user7" → "USER7" → 길이 5
            assertEquals(5, AsyncPipeline.profileNameLength(7));
        });
    }

    @Test
    @DisplayName("thenApply 로 결과를 대문자로 변환한다")
    void pipelineToUpperTransforms() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals("HELLO", AsyncPipeline.pipelineToUpper("hello"));
        });
    }

    @Test
    @DisplayName("thenAccept(소비) 후 thenRun(후속) 이 차례로 실행된다")
    void acceptThenRunRunsInOrder() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 100 더하고(thenAccept) + 1 더 올림(thenRun) = 101
            assertEquals(101, AsyncPipeline.acceptThenRun());
        });
    }
}
