package com.instagram.javabasic.design.ocp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OcpDesignTest {

    // ===== 기본: Email + Push 채널로 한 번에 알림을 보낸다 =====

    @Test
    @DisplayName("broadcast: Email·Push 채널 배열에 각 채널 형식대로 메시지가 전달된다")
    void broadcast_toEmailAndPush() {
        Notifier notifier = new Notifier();
        NotificationChannel[] channels = {new EmailChannel(), new PushChannel()};

        String[] results = notifier.broadcast(channels, "새 좋아요가 있어요");

        assertEquals(2, results.length);
        assertEquals("[Email] 새 좋아요가 있어요", results[0]);
        assertEquals("[Push] 새 좋아요가 있어요", results[1]);
    }

    // ===== OCP 핵심: 새 채널을 추가해도 Notifier 는 한 줄도 안 바뀐다 =====

    @Test
    @DisplayName("확장: 새 SmsChannel 을 추가해도 Notifier 수정 없이 그대로 동작한다")
    void broadcast_worksWithNewChannelWithoutModifyingNotifier() {
        Notifier notifier = new Notifier();

        // Notifier 코드는 손대지 않고, 새 채널만 배열에 끼워넣어요
        NotificationChannel[] channels = {
                new EmailChannel(),
                new PushChannel(),
                new SmsChannel()
        };

        String[] results = notifier.broadcast(channels, "팔로우 요청");

        assertEquals(3, results.length);
        assertEquals("[Email] 팔로우 요청", results[0]);
        assertEquals("[Push] 팔로우 요청", results[1]);
        assertEquals("[SMS] 팔로우 요청", results[2]);
    }
}
