package com.instagram.javabasic.exceptionbasic;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SignupValidatorV2Test {

    private final SignupValidatorV2 validator = new SignupValidatorV2();

    // 호출자 입장에서 catch 를 타입별로 분리 처리할 수 있는지 확인하는 도우미.
    // 예외 타입마다 다른 안내 문구를 돌려준다.
    private String tryToSignup(String username, int age) {
        try {
            validator.validate(username, age);
            return "가입 성공";
        } catch (InvalidUsernameException e) {
            return "이름을 확인해주세요 → " + e.getMessage();
        } catch (UnderageMemberException e) {
            return "가입 연령을 확인해주세요 → " + e.getMessage();
        }
    }

    @Test
    @DisplayName("모든 규칙을 통과하면 예외 없이 조용히 끝난다")
    void 정상가입() {
        assertDoesNotThrow(() -> validator.validate("minji", 25));
    }

    @Test
    @DisplayName("빈 이름이면 InvalidUsernameException 이 던져진다")
    void 빈이름() {
        InvalidUsernameException e = assertThrows(InvalidUsernameException.class,
                () -> validator.validate("", 25));
        assertEquals("사용자 이름을 입력해주세요.", e.getMessage());
    }

    @Test
    @DisplayName("null 이름도 InvalidUsernameException 이 던져진다")
    void null이름() {
        assertThrows(InvalidUsernameException.class, () -> validator.validate(null, 25));
    }

    @Test
    @DisplayName("30자를 넘는 이름이면 InvalidUsernameException 이 던져진다")
    void 너무긴이름() {
        String longName = "a".repeat(31);
        InvalidUsernameException e = assertThrows(InvalidUsernameException.class,
                () -> validator.validate(longName, 25));
        assertEquals("사용자 이름은 30자까지예요.", e.getMessage());
    }

    @Test
    @DisplayName("만 14세 미만이면 UnderageMemberException 이 던져진다")
    void 나이미달() {
        UnderageMemberException e = assertThrows(UnderageMemberException.class,
                () -> validator.validate("jaehoon", 13));
        assertEquals("만 14세 이상만 가입할 수 있어요.", e.getMessage());
    }

    @Test
    @DisplayName("호출자는 catch 를 타입별로 나눠 사고마다 다르게 대응할 수 있다")
    void 타입별_분리처리() {
        assertEquals("가입 성공", tryToSignup("minji", 25));
        assertEquals("이름을 확인해주세요 → 사용자 이름을 입력해주세요.", tryToSignup("", 25));
        assertEquals("가입 연령을 확인해주세요 → 만 14세 이상만 가입할 수 있어요.",
                tryToSignup("jaehoon", 13));
    }

    @Test
    @DisplayName("검증용 예외 2종도 RuntimeException 계열이라 throws 선언 없이 던질 수 있다")
    void 언체크_계열확인() {
        RuntimeException usernameCase = assertThrows(RuntimeException.class,
                () -> validator.validate("", 25));
        RuntimeException ageCase = assertThrows(RuntimeException.class,
                () -> validator.validate("jaehoon", 10));
        assertEquals(InvalidUsernameException.class, usernameCase.getClass());
        assertEquals(UnderageMemberException.class, ageCase.getClass());
    }
}
