package com.instagram.javabasic.design.behavioral.strategy;

// com/instagram/javabasic/design/behavioral/strategy/NotificationSender.java

import com.instagram.javabasic.design.ocp.NotificationChannel;

// 알림을 "어떤 채널로 보낼지" 라는 전략을 품고 있는 클래스예요.
// 정작 보내는 일은 자기가 직접 하지 않고, 품고 있는 전략(채널)에 맡겨요.
// 그래서 채널을 통째로 갈아끼워도 이 클래스 자체는 한 줄도 안 바뀌어요.
public class NotificationSender {

    // 지금 사용 중인 전략(알림 채널)이에요. 런타임에 다른 채널로 바꿀 수 있어요.
    private NotificationChannel channel;

    // 처음 만들 때 어떤 전략으로 시작할지 외부에서 받아요.
    public NotificationSender(NotificationChannel channel) {
        this.channel = channel;
    }

    // 보내던 도중에도 전략(채널)을 새것으로 갈아끼워요.
    // 이 메서드 하나로 이메일 → 푸시 → 문자처럼 동작을 바꿀 수 있어요.
    public void changeChannel(NotificationChannel channel) {
        this.channel = channel;
    }

    // 실제로 보낼 때는 직접 하지 않고, 지금 품고 있는 전략에게 맡겨요.
    public String notify(String message) {
        return channel.send(message);
    }
}
