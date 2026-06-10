package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SignupValidatorTest {

    @Test
    @DisplayName("모든 규칙을 통과하면 예외 없이 조용히 끝난다")
    void validate_정상() {
        SignupValidator validator = new SignupValidator();
        assertDoesNotThrow(() -> validator.validate("minji", 25));
    }

    @Test
    @DisplayName("이름이 비어 있으면 IllegalArgumentException 을 던진다")
    void validate_빈이름() {
        SignupValidator validator = new SignupValidator();
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate("", 25));
        assertEquals("사용자 이름을 입력해주세요.", e.getMessage());
    }

    @Test
    @DisplayName("이름이 30자를 넘으면 IllegalArgumentException 을 던진다")
    void validate_이름초과() {
        SignupValidator validator = new SignupValidator();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 31; i++) {
            sb.append("a");
        }
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate(sb.toString(), 25));
        assertEquals("사용자 이름은 30자까지예요.", e.getMessage());
    }

    @Test
    @DisplayName("만 14세 미만이면 IllegalArgumentException 을 던진다")
    void validate_나이미달() {
        SignupValidator validator = new SignupValidator();
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> validator.validate("jaehoon", 13));
        assertEquals("만 14세 이상만 가입할 수 있어요.", e.getMessage());
    }

    @Test
    @DisplayName("정확히 만 14세는 통과한다(경계값)")
    void validate_나이경계값() {
        SignupValidator validator = new SignupValidator();
        assertDoesNotThrow(() -> validator.validate("seungwoo", 14));
    }
}
