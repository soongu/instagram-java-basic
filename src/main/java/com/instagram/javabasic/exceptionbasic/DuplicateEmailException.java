package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/DuplicateEmailException.java
// 이미 가입된 이메일로 다시 가입하려 할 때 던지는 우리 서비스 전용 예외예요.
// "회원이 없어요" 와 "이메일이 겹쳐요" 는 전혀 다른 사고인데, 둘 다
// IllegalArgumentException 으로 던지면 잡는 쪽이 구분할 수 없어요.
// 사고마다 예외 이름을 따로 두면 catch 를 타입별로 나눠 다르게 대응할 수 있어요.
public class DuplicateEmailException extends RuntimeException {

    // 메시지만 담아 만들어요 — 어떤 이메일이 겹쳤는지를 글로 남겨요.
    public DuplicateEmailException(String message) {
        super(message);
    }

    // 메시지 + 원인(cause)을 함께 담아 만들어요 — 처음 사고의 흔적을 잃지 않고 보관해요.
    public DuplicateEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
