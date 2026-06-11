package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;

class OptionalExtractDemoTest {

    private final Member joined = new Member("jaehoon", "jaehoon@example.com");
    private final Member numbersOnly = new Member("minji", 8500, 150, 5, 365);

    @Test
    @DisplayName("orElse: 비어 있으면 준비한 기본값")
    void orElse() {
        assertEquals("이메일 미등록", OptionalExtractDemo.emailOrDefault(numbersOnly));
        assertEquals("jaehoon@example.com", OptionalExtractDemo.emailOrDefault(joined));
    }

    @Test
    @DisplayName("orElseGet: 비어 있을 때만 만들어 채움")
    void orElseGet() {
        assertEquals("guest-minji@temp.com", OptionalExtractDemo.emailOrComputed(numbersOnly));
        assertEquals("jaehoon@example.com", OptionalExtractDemo.emailOrComputed(joined));
    }

    @Test
    @DisplayName("orElseThrow: 값이 있으면 그대로 돌려줌")
    void orElseThrowPresent() {
        assertEquals("jaehoon@example.com", OptionalExtractDemo.emailOrThrow(joined));
    }

    @Test
    @DisplayName("orElseThrow: 비어 있으면 MemberNotFoundException")
    void orElseThrowEmpty() {
        assertThrows(MemberNotFoundException.class,
                () -> OptionalExtractDemo.emailOrThrow(numbersOnly));
    }
}
