package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class OptionalCheckDemoTest {

    private final Member joined = new Member("jaehoon", "jaehoon@example.com");
    private final Member numbersOnly = new Member("minji", 8500, 150, 5, 365);

    @Test
    @DisplayName("isPresent: 이메일이 있으면 true")
    void hasEmail() {
        assertTrue(OptionalCheckDemo.hasEmail(joined));
        assertFalse(OptionalCheckDemo.hasEmail(numbersOnly));
    }

    @Test
    @DisplayName("isEmpty: 이메일이 없으면 true")
    void missingEmail() {
        assertTrue(OptionalCheckDemo.missingEmail(numbersOnly));
        assertFalse(OptionalCheckDemo.missingEmail(joined));
    }

    @Test
    @DisplayName("ifPresent: 값이 있을 때만 람다 실행")
    void ifPresentWithEmail() {
        assertEquals("회원 확인 · 이메일 jaehoon@example.com",
                OptionalCheckDemo.ifPresentMessage(joined));
    }

    @Test
    @DisplayName("ifPresent: 비어 있으면 아무 일도 안 함")
    void ifPresentWithoutEmail() {
        assertEquals("회원 확인", OptionalCheckDemo.ifPresentMessage(numbersOnly));
    }

    @Test
    @DisplayName("get: 빈 상자에 부르면 NoSuchElementException")
    void dangerousGetThrows() {
        assertThrows(NoSuchElementException.class,
                () -> OptionalCheckDemo.dangerousGet(numbersOnly));
    }
}
