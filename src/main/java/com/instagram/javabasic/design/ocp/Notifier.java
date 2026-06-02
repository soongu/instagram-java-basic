package com.instagram.javabasic.design.ocp;

// com/instagram/javabasic/design/ocp/Notifier.java
// 여러 채널에 한 번에 알림을 뿌리는 발송기예요.
// 중요한 점 — Notifier 는 구체적인 채널(EmailChannel·PushChannel)을 전혀 몰라요.
// 오직 NotificationChannel 이라는 약속(인터페이스)만 알고, 그 send 만 불러요.
// 그래서 새 채널(SmsChannel)이 추가돼도 이 클래스는 한 줄도 바뀌지 않아요.
public class Notifier {

    // 채널 배열을 받아 각 채널에 send 를 부르고, 결과들을 배열로 모아 돌려줘요.
    public String[] broadcast(NotificationChannel[] channels, String message) {
        String[] results = new String[channels.length];
        for (int i = 0; i < channels.length; i++) {
            results[i] = channels[i].send(message);
        }
        return results;
    }
}
