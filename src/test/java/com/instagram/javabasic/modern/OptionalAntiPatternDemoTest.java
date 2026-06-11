package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class OptionalAntiPatternDemoTest {

    private final Member joined = new Member("jaehoon", "jaehoon@example.com");
    private final Member numbersOnly = new Member("minji", 8500, 150, 5, 365);

    @Test
    @DisplayName("번거로운 방식과 깔끔한 방식은 결과가 같다 (이메일 있음)")
    void sameResultPresent() {
        assertEquals(OptionalAntiPatternDemo.clumsy(joined),
                OptionalAntiPatternDemo.clean(joined));
        assertEquals("jaehoon@example.com", OptionalAntiPatternDemo.clean(joined));
    }

    @Test
    @DisplayName("번거로운 방식과 깔끔한 방식은 결과가 같다 (이메일 없음)")
    void sameResultEmpty() {
        assertEquals(OptionalAntiPatternDemo.clumsy(numbersOnly),
                OptionalAntiPatternDemo.clean(numbersOnly));
        assertEquals("이메일 미등록", OptionalAntiPatternDemo.clean(numbersOnly));
    }
}
