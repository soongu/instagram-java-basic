package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AsyncErrorHandlingTest {

    @Test
    @DisplayName("exceptionally: 성공이면 결과를, 실패면 기본값(-1)을 돌려준다")
    void exceptionallyFallsBackToDefault() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals(50, AsyncErrorHandling.loadLikesOrDefault(5, false));
            assertEquals(-1, AsyncErrorHandling.loadLikesOrDefault(5, true));
        });
    }

    @Test
    @DisplayName("handle: 성공과 실패를 한자리에서 갈라 처리한다")
    void handleProcessesBothBranches() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            assertEquals("좋아요 50", AsyncErrorHandling.handleBoth(5, false));
            assertEquals("실패: 기본값 사용", AsyncErrorHandling.handleBoth(5, true));
        });
    }

    @Test
    @DisplayName("whenComplete: 결과를 바꾸지 않고 엿보기만 한다")
    void whenCompletePeeksWithoutChanging() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 4 * 10 = 40
            assertEquals(40, AsyncErrorHandling.peekWithWhenComplete(4));
        });
    }
}
