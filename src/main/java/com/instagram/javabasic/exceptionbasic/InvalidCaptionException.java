package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/InvalidCaptionException.java
// 게시물 캡션이 규칙(글자 수 제한, 금지어 등)에 어긋날 때 던지는 우리 서비스 전용 예외예요.
// 예외 이름이 곧 문서 역할을 해요 — 코드 어디선가 이 예외가 보이면
// "캡션 검증에서 막혔구나" 를 설명 없이도 알 수 있어요.
public class InvalidCaptionException extends RuntimeException {

    // 메시지만 담아 만들어요 — 어떤 규칙에 걸렸는지를 글로 남겨요.
    public InvalidCaptionException(String message) {
        super(message);
    }

    // 메시지 + 원인(cause)을 함께 담아 만들어요 — 처음 사고의 흔적을 잃지 않고 보관해요.
    public InvalidCaptionException(String message, Throwable cause) {
        super(message, cause);
    }
}
