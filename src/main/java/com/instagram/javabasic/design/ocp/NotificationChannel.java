package com.instagram.javabasic.design.ocp;

// com/instagram/javabasic/design/ocp/NotificationChannel.java
// "알림을 보낼 수 있다" 는 역할을 약속하는 인터페이스예요.
// 이메일이든 푸시든 문자든, 알림 채널이라면 모두 send 를 할 줄 알아야 해요.
// 새 채널이 생길 때 이 약속만 지키면 기존 코드(Notifier)는 그대로 둬도 돼요.
public interface NotificationChannel {

    // 메시지를 채널 형식으로 보내고, 보낸 결과 문구를 돌려줘요.
    // 본문이 없는 빈칸이에요 — 채우는 건 구현 클래스의 몫이에요.
    String send(String message);
}
