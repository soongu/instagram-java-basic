package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.concurrent.ProfileScreenLoader.ProfileScreen;

class ProfileScreenLoaderTest {

    @Test
    @DisplayName("세 호출을 병렬로 시작해 조립한 프로필 화면이 기대 record 와 같다")
    void loadScreenAssemblesParallelResults() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // 본문 "post#2 본문", 댓글 2*5=10, 좋아요 2*10=20
            assertEquals(
                    new ProfileScreen("post#2 본문", 10, 20),
                    ProfileScreenLoader.loadScreen(2));
        });
    }
}
