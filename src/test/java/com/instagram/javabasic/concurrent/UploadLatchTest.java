package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UploadLatchTest {

    @Test
    @DisplayName("이미지 8장이 모두 업로드되면 await 가 풀려 게시물 공개가 가능해진다")
    void publishesAfterAllUploadsFinish() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 모든 일감이 countDown 하면 latch 가 0 이 되어 await 가 풀리므로 항상 true 다.
            assertTrue(UploadLatch.publishWhenAllUploaded(8),
                    "8장이 모두 올라가면 공개 준비가 끝나야 한다");
        });
    }
}
