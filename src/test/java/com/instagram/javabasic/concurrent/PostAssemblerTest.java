package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostAssemblerTest {

    @Test
    @DisplayName("thenCombine 으로 본문과 좋아요 수를 합쳐 한 줄로 렌더링한다")
    void renderPostCombinesTwoResults() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 본문 "post#3 본문" + 좋아요 3*10=30
            assertEquals("post#3 본문 (좋아요 30)", PostAssembler.renderPost(3));
        });
    }
}
