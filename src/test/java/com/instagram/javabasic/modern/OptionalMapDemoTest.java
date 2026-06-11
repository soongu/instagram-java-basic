package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class OptionalMapDemoTest {

    private final Member joined = new Member("jaehoon", "jaehoon@example.com");
    private final Member numbersOnly = new Member("minji", 8500, 150, 5, 365);

    @Test
    @DisplayName("map: 이메일 문자열을 길이(숫자)로 변환")
    void emailLengthPresent() {
        assertEquals(19, OptionalMapDemo.emailLength(joined).get());
    }

    @Test
    @DisplayName("map: 비어 있으면 변환을 건너뛰고 빈 상자")
    void emailLengthEmpty() {
        assertTrue(OptionalMapDemo.emailLength(numbersOnly).isEmpty());
    }

    @Test
    @DisplayName("map + orElse: 이메일 도메인만 뽑기")
    void emailDomain() {
        assertEquals("example.com", OptionalMapDemo.emailDomain(joined));
    }

    @Test
    @DisplayName("map + orElse: 이메일이 없으면 기본값")
    void emailDomainEmpty() {
        assertEquals("(도메인 없음)", OptionalMapDemo.emailDomain(numbersOnly));
    }
}
