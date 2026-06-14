package com.instagram.javabasic.design.structural.decorator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.design.behavioral.strategy.NotificationSender;
import com.instagram.javabasic.design.ocp.EmailChannel;
import com.instagram.javabasic.design.ocp.PushChannel;

class DecoratorTest {

    @Test
    @DisplayName("이모지 한 겹을 덧입히면 결과 앞에 알림 이모지가 붙는다")
    void wrapsWithEmoji() {
        EmojiChannel channel = new EmojiChannel(new EmailChannel());

        assertEquals("🔔 [Email] 새 댓글", channel.send("새 댓글"));
    }

    @Test
    @DisplayName("중요 표시 한 겹을 덧입히면 결과 뒤에 (중요)가 붙는다")
    void wrapsWithUrgent() {
        UrgentChannel channel = new UrgentChannel(new EmailChannel());

        assertEquals("[Email] 좋아요 (중요)", channel.send("좋아요"));
    }

    @Test
    @DisplayName("데코가 데코를 감싸면 두 겹이 함께 덧입혀진다")
    void wrapsTwice() {
        UrgentChannel channel = new UrgentChannel(new EmojiChannel(new EmailChannel()));

        assertEquals("🔔 [Email] 팔로우 (중요)", channel.send("팔로우"));
    }

    @Test
    @DisplayName("데코레이터도 결국 NotificationChannel이라 NotificationSender에 그대로 끼워진다")
    void worksAsNotificationChannel() {
        NotificationSender sender = new NotificationSender(new EmojiChannel(new PushChannel()));

        assertEquals("🔔 [Push] DM", sender.notify("DM"));
    }
}
