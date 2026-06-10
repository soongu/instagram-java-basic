package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/UnderageMemberException.java
// 회원 가입에서 나이 규칙(만 14세 이상)에 어긋날 때 던지는 예외예요.
// 이름 문제(InvalidUsernameException)와 타입이 다르니, 잡는 쪽은
// "나이 문제는 보호자 동의 안내로, 이름 문제는 입력 안내로" 처럼 다르게 대응할 수 있어요.
public class UnderageMemberException extends RuntimeException {

    // 메시지만 담아 만들어요 — 가입 연령 기준을 글로 남겨요.
    public UnderageMemberException(String message) {
        super(message);
    }

    // 메시지 + 원인(cause)을 함께 담아 만들어요 — 처음 사고의 흔적을 잃지 않고 보관해요.
    public UnderageMemberException(String message, Throwable cause) {
        super(message, cause);
    }
}
