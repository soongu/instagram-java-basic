package com.instagram.javabasic.design.ocp;

// com/instagram/javabasic/design/ocp/PushChannel.java
// 휴대폰 푸시로 알림을 보내는 채널이에요. NotificationChannel 약속을 지켜요.
public class PushChannel implements NotificationChannel {

    @Override
    public String send(String message) {
        return "[Push] " + message;
    }
}
