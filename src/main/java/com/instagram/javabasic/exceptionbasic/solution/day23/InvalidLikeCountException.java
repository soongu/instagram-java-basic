package com.instagram.javabasic.exceptionbasic.solution.day23;

// com/instagram/javabasic/exceptionbasic/solution/day23/InvalidLikeCountException.java
// [기초] 과제 1 — 좋아요 수가 잘못됐을 때 던지는 우리 서비스 전용 예외예요.
// IllegalArgumentException 대신 이름에 "좋아요 수가 잘못됐다" 를 담아서,
// 잡는 쪽이 예외 이름만 보고 무슨 사고인지 바로 알 수 있어요.
// RuntimeException 을 상속한 언체크 예외라 throws 선언 없이 던질 수 있어요.
public class InvalidLikeCountException extends RuntimeException {

    // 메시지만 담아 만들어요 — 어떤 값이 잘못됐는지를 글로 남겨요.
    public InvalidLikeCountException(String message) {
        super(message);
    }

    // 메시지 + 원인(cause)을 함께 담아 만들어요 — 처음 사고의 흔적을 보존해요.
    public InvalidLikeCountException(String message, Throwable cause) {
        super(message, cause);
    }
}
