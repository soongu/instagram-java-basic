package com.instagram.javabasic.design.behavioral.strategy;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.design.ocp.EmailChannel;
import com.instagram.javabasic.design.ocp.PushChannel;

class NotificationSenderTest {

    @Test
    @DisplayName("생성 시 받은 채널로 알림이 나간다")
    void usesInjectedChannel() {
        NotificationSender sender = new NotificationSender(new EmailChannel());

        assertEquals("[Email] 새 댓글", sender.notify("새 댓글"));
    }

    @Test
    @DisplayName("런타임에 채널을 갈아끼우면 그다음부터 새 채널로 나간다")
    void changesChannelAtRuntime() {
        NotificationSender sender = new NotificationSender(new EmailChannel());
        assertEquals("[Email] 좋아요", sender.notify("좋아요"));

        sender.changeChannel(new PushChannel());

        assertEquals("[Push] 좋아요", sender.notify("좋아요"));
    }

    @Test
    @DisplayName("단일 메서드 인터페이스라 람다도 전략이 될 수 있다")
    void lambdaCanBeStrategy() {
        NotificationSender sender = new NotificationSender(msg -> "[Slack] " + msg);

        assertEquals("[Slack] hi", sender.notify("hi"));
    }
}
