package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/InvalidUsernameException.java
// 회원 가입에서 사용자 이름이 규칙(빈 값 금지, 30자 제한)에 어긋날 때 던지는 예외예요.
// "어떤 검사에서 막혔는지" 가 예외 타입으로 드러나서, 잡는 쪽이 이름 문제와
// 나이 문제를 catch 블록으로 따로따로 대응할 수 있어요.
public class InvalidUsernameException extends RuntimeException {

    // 메시지만 담아 만들어요 — 어떤 이름 규칙에 걸렸는지를 글로 남겨요.
    public InvalidUsernameException(String message) {
        super(message);
    }

    // 메시지 + 원인(cause)을 함께 담아 만들어요 — 처음 사고의 흔적을 잃지 않고 보관해요.
    public InvalidUsernameException(String message, Throwable cause) {
        super(message, cause);
    }
}
