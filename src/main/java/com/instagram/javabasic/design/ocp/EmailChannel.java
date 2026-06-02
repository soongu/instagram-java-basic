package com.instagram.javabasic.design.ocp;

// com/instagram/javabasic/design/ocp/EmailChannel.java
// 이메일로 알림을 보내는 채널이에요. NotificationChannel 약속을 지켜요.
public class EmailChannel implements NotificationChannel {

    @Override
    public String send(String message) {
        return "[Email] " + message;
    }
}
