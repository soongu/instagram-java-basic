package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class NullProblemDemoTest {

    @Test
    @DisplayName("safeEmailLength: 이메일이 있으면 글자 수")
    void safeWithEmail() {
        Member joined = new Member("jaehoon", "jaehoon@example.com");
        assertEquals(19, NullProblemDemo.safeEmailLength(joined));
    }

    @Test
    @DisplayName("safeEmailLength: 이메일이 null 이면 0 (방어 코드)")
    void safeWithoutEmail() {
        Member numbersOnly = new Member("minji", 8500, 150, 5, 365);
        assertEquals(0, NullProblemDemo.safeEmailLength(numbersOnly));
    }

    @Test
    @DisplayName("unsafeEmailLength: 이메일이 null 이면 NullPointerException")
    void unsafeThrows() {
        Member numbersOnly = new Member("minji", 8500, 150, 5, 365);
        assertThrows(NullPointerException.class,
                () -> NullProblemDemo.unsafeEmailLength(numbersOnly));
    }

    @Test
    @DisplayName("unsafeEmailLength: 이메일이 있으면 정상 동작")
    void unsafeOk() {
        Member joined = new Member("jaehoon", "jaehoon@example.com");
        assertEquals(19, NullProblemDemo.unsafeEmailLength(joined));
    }
}
