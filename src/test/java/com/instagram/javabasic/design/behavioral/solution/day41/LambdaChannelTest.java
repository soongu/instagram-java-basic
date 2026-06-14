package com.instagram.javabasic.design.behavioral.solution.day41;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.design.behavioral.strategy.NotificationSender;

class LambdaChannelTest {

    @Test
    @DisplayName("카카오 채널을 람다로 추가해도 NotificationSender 는 그대로 동작한다")
    void kakaoChannel() {
        // NotificationChannel 은 메서드가 하나뿐이라 람다로 바로 전략을 만들 수 있어요.
        // 새 채널을 위해 클래스를 따로 만들 필요가 없어요.
        NotificationSender sender = new NotificationSender(message -> "[KaKao] " + message);

        String result = sender.notify("새 댓글이 달렸어요");

        assertEquals("[KaKao] 새 댓글이 달렸어요", result);
    }

    @Test
    @DisplayName("디스코드 채널도 람다로 끼울 수 있다")
    void discordChannel() {
        NotificationSender sender = new NotificationSender(message -> "[KaKao] " + message);

        // 보내던 도중에 디스코드 채널 람다로 전략을 갈아끼워요.
        sender.changeChannel(message -> "[Discord] " + message);
        String result = sender.notify("새 댓글이 달렸어요");

        assertEquals("[Discord] 새 댓글이 달렸어요", result);
    }
}
