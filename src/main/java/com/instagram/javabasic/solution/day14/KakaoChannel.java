package com.instagram.javabasic.solution.day14;

import com.instagram.javabasic.design.ocp.NotificationChannel;

// com/instagram/javabasic/solution/day14/KakaoChannel.java
// 과제 2 — 카카오톡으로 알림을 보내는 '새 채널' 이에요.
// 핵심은 — 이 파일을 새로 만들었을 뿐, Notifier·EmailChannel·PushChannel 은
// 한 줄도 바꾸지 않았다는 점이에요. NotificationChannel 약속만 지키면
// 기존 발송기(Notifier)가 이 채널도 알아서 함께 보내줘요.
public class KakaoChannel implements NotificationChannel {

    @Override
    public String send(String message) {
        return "[Kakao] " + message;
    }
}
