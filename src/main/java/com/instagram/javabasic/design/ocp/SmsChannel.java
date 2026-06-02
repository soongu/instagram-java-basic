package com.instagram.javabasic.design.ocp;

// com/instagram/javabasic/design/ocp/SmsChannel.java
// 나중에 새로 추가된 문자(SMS) 채널이에요.
// 핵심은 — 이 파일을 새로 만들었을 뿐, Notifier 는 한 줄도 바꾸지 않았다는 점이에요.
// "확장에는 열려 있고, 기존 코드 수정에는 닫혀 있다" 가 바로 이 모습이에요.
public class SmsChannel implements NotificationChannel {

    @Override
    public String send(String message) {
        return "[SMS] " + message;
    }
}
