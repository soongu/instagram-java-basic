package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CustomExceptionTest {

    @Test
    @DisplayName("MemberNotFoundException: 던지고 잡아 메시지를 확인한다")
    void memberNotFound_메시지() {
        MemberNotFoundException e = assertThrows(MemberNotFoundException.class, () -> {
            throw new MemberNotFoundException("회원을 찾을 수 없어요: id 99");
        });
        assertEquals("회원을 찾을 수 없어요: id 99", e.getMessage());
    }

    @Test
    @DisplayName("MemberNotFoundException: cause 생성자로 만든 예외는 getCause 로 원인을 돌려준다")
    void memberNotFound_원인보관() {
        IllegalArgumentException origin = new IllegalArgumentException("id 99 에 해당하는 항목이 없어요.");
        MemberNotFoundException e = new MemberNotFoundException("회원을 찾을 수 없어요: id 99", origin);
        assertEquals(origin, e.getCause());
    }

    @Test
    @DisplayName("DuplicateEmailException: 던지고 잡아 메시지를 확인한다")
    void duplicateEmail_메시지() {
        DuplicateEmailException e = assertThrows(DuplicateEmailException.class, () -> {
            throw new DuplicateEmailException("이미 가입된 이메일이에요: minji@insta.com");
        });
        assertEquals("이미 가입된 이메일이에요: minji@insta.com", e.getMessage());
    }

    @Test
    @DisplayName("DuplicateEmailException: cause 생성자로 만든 예외는 getCause 로 원인을 돌려준다")
    void duplicateEmail_원인보관() {
        IllegalStateException origin = new IllegalStateException("저장소에 같은 이메일이 이미 있어요.");
        DuplicateEmailException e = new DuplicateEmailException("이미 가입된 이메일이에요.", origin);
        assertEquals(origin, e.getCause());
    }

    @Test
    @DisplayName("InvalidCaptionException: 던지고 잡아 메시지를 확인한다")
    void invalidCaption_메시지() {
        InvalidCaptionException e = assertThrows(InvalidCaptionException.class, () -> {
            throw new InvalidCaptionException("캡션은 2200자까지만 쓸 수 있어요.");
        });
        assertEquals("캡션은 2200자까지만 쓸 수 있어요.", e.getMessage());
    }

    @Test
    @DisplayName("InvalidCaptionException: cause 생성자로 만든 예외는 getCause 로 원인을 돌려준다")
    void invalidCaption_원인보관() {
        IllegalArgumentException origin = new IllegalArgumentException("문자 수 계산 중 규칙 위반이 발견됐어요.");
        InvalidCaptionException e = new InvalidCaptionException("캡션 규칙에 어긋나요.", origin);
        assertEquals(origin, e.getCause());
    }

    @Test
    @DisplayName("커스텀 예외 3종은 모두 RuntimeException 으로도 잡을 수 있다")
    void 셋다_RuntimeException_계열() {
        RuntimeException e = assertThrows(RuntimeException.class, () -> {
            throw new MemberNotFoundException("회원을 찾을 수 없어요.");
        });
        assertInstanceOf(MemberNotFoundException.class, e);
        assertInstanceOf(RuntimeException.class, new DuplicateEmailException("중복 이메일"));
        assertInstanceOf(RuntimeException.class, new InvalidCaptionException("캡션 위반"));
    }
}
