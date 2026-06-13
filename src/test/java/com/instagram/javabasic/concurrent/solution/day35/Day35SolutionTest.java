package com.instagram.javabasic.concurrent.solution.day35;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Day35SolutionTest {

    @Test
    @DisplayName("[과제 1] 알림 20건을 스레드풀에 던지면 try-with-resources 덕분에 항상 20건이 발송된다")
    void 알림_전부_발송() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // close() 가 모든 일감 완료를 기다려주므로 결과는 항상 정확히 20이다.
            assertEquals(20, NotificationDispatcher.dispatchAll(20));
        });
    }

    @Test
    @DisplayName("[과제 2] 여러 게시물 좋아요 수를 Future 로 모아 거두면 정확히 합산된다")
    void 좋아요_동시_합산() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 10 + 20 + 30 = 60. Future 를 먼저 모은 뒤 get 으로 거둬도 합은 결정적이다.
            assertEquals(60, LikeAggregator.totalLikes(new int[] {10, 20, 30}));
        });
    }

    @Test
    @DisplayName("[과제 3] 8장이 모두 업로드되면 공개 가능하고 성공 수가 정확히 8이다")
    void 업로드_완료_후_공개와_성공수() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            AlbumPublisher.PublishResult result = AlbumPublisher.publish(8);
            assertTrue(result.published(), "8장이 모두 끝나면 공개 가능(await 풀림)해야 한다");
            // Atomic 으로 세고 countDown 을 finally 에서 보장하므로 성공 수는 항상 정확히 8이다.
            assertEquals(8, result.successCount(), "성공한 업로드 수는 정확히 8이어야 한다");
        });
    }
}
