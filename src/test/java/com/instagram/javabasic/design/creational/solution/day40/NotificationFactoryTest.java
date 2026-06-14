package com.instagram.javabasic.design.creational.solution.day40;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.design.creational.solution.day40.NotificationFactory.NotificationType;

class NotificationFactoryTest {

    @Test
    @DisplayName("LIKE 종류를 주면 좋아요 알림이 만들어진다")
    void like() {
        Notification n = NotificationFactory.create(NotificationType.LIKE, "jaehoon");

        assertInstanceOf(LikeNotification.class, n);
        assertTrue(n.message().contains("좋아합니다"));
    }

    @Test
    @DisplayName("COMMENT 종류를 주면 댓글 알림이 만들어진다")
    void comment() {
        Notification n = NotificationFactory.create(NotificationType.COMMENT, "minji");

        assertInstanceOf(CommentNotification.class, n);
        assertTrue(n.message().contains("댓글"));
    }

    @Test
    @DisplayName("FOLLOW 종류를 주면 팔로우 알림이 만들어진다")
    void follow() {
        Notification n = NotificationFactory.create(NotificationType.FOLLOW, "seungwoo");

        assertInstanceOf(FollowNotification.class, n);
        assertTrue(n.message().contains("팔로우"));
    }
}
