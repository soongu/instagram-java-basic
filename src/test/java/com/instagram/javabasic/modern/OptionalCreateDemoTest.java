package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class OptionalCreateDemoTest {

    @Test
    @DisplayName("of: 이름은 항상 있으니 값이 든 상자")
    void wrapUsername() {
        Member joined = new Member("jaehoon", "jaehoon@example.com");
        assertTrue(OptionalCreateDemo.wrapUsername(joined).isPresent());
        assertEquals("jaehoon", OptionalCreateDemo.wrapUsername(joined).get());
    }

    @Test
    @DisplayName("of: null 을 넣으면 그 자리에서 NullPointerException")
    void ofNullThrows() {
        Member empty = new Member(); // username 이 null
        assertThrows(NullPointerException.class,
                () -> OptionalCreateDemo.wrapUsername(empty));
    }

    @Test
    @DisplayName("ofNullable: 이메일이 있으면 값이 든 상자")
    void wrapEmailPresent() {
        Member joined = new Member("jaehoon", "jaehoon@example.com");
        assertTrue(OptionalCreateDemo.wrapEmail(joined).isPresent());
    }

    @Test
    @DisplayName("ofNullable: 이메일이 null 이면 빈 상자")
    void wrapEmailEmpty() {
        Member numbersOnly = new Member("minji", 8500, 150, 5, 365);
        assertFalse(OptionalCreateDemo.wrapEmail(numbersOnly).isPresent());
    }

    @Test
    @DisplayName("empty: 처음부터 빈 상자")
    void emptyBox() {
        assertTrue(OptionalCreateDemo.emptyBox().isEmpty());
    }
}
