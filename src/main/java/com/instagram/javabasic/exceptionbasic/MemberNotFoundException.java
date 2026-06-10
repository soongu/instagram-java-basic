package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/MemberNotFoundException.java
// 회원을 id 나 이름으로 찾았는데 없을 때 던지는 우리 서비스 전용 예외예요.
// IllegalArgumentException 같은 자바 기본 예외 대신 이름에 상황을 그대로 담아서,
// 잡는 쪽이 "아, 회원 조회가 실패했구나" 를 예외 이름만 보고 바로 알 수 있어요.
// RuntimeException 을 상속했으니 throws 선언 없이 던질 수 있는 언체크 예외예요.
public class MemberNotFoundException extends RuntimeException {

    // 메시지만 담아 만들어요 — "누구를 못 찾았는지" 를 글로 남겨요.
    public MemberNotFoundException(String message) {
        super(message);
    }

    // 메시지 + 원인(cause)을 함께 담아 만들어요 — 처음 사고의 흔적을 잃지 않고 보관해요.
    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
