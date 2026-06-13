package com.instagram.javabasic.concurrent.solution.day37;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FollowerNotifierTest {

    @Test
    @DisplayName("팔로워 500명에게 가상 스레드로 알림을 빠짐없이 보낸다")
    void notifiesAllFollowers() {
        assertTimeoutPreemptively(Duration.ofSeconds(10), () -> {
            // try-with-resources 의 close() 가 모든 알림 완료를 기다리므로 항상 500이다.
            assertEquals(500, FollowerNotifier.notifyFollowers(500));
        });
    }

    @Test
    @DisplayName("팔로워가 0명이면 보낸 알림도 0이다")
    void noFollowersNoNotifications() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            assertEquals(0, FollowerNotifier.notifyFollowers(0));
        });
    }
}
