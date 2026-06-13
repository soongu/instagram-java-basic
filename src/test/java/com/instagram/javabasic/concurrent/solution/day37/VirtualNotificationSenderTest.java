package com.instagram.javabasic.concurrent.solution.day37;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

import java.time.Duration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class VirtualNotificationSenderTest {

    @Test
    @DisplayName("메시지 3건을 가상 스레드로 전부 보내면 3이 반환된다")
    void sendsAllOnVirtualThreads() {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            String[] messages = {"새 게시물 알림", "좋아요 알림", "댓글 알림"};
            // isVirtual() 이 true 일 때만 세므로, 3이 나온다는 건 셋 다 가상 스레드에서 돌았다는 뜻.
            assertEquals(3, VirtualNotificationSender.sendAll(messages));
        });
    }

    @Test
    @DisplayName("메시지가 없으면 보낸 알림은 0이다")
    void emptyMessagesSendNothing() {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            assertEquals(0, VirtualNotificationSender.sendAll(new String[] {}));
        });
    }
}
