package com.instagram.javabasic.concurrent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ProfileChainTest {

    @Test
    @DisplayName("thenCompose 로 비동기를 비동기로 이어 붙여 팔로워 수를 계산한다")
    void followerCountChainsAsync() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // (7 + 1000) = 1007, 1007 * 2 = 2014
            assertEquals(2014, ProfileChain.followerCountOf(7));
        });
    }
}
