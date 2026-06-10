package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class MethodReferenceDemoTest {

    @Test
    @DisplayName("타입의 인스턴스 메서드 참조 — Member::getUsername")
    void instanceMethodReference() {
        Function<Member, String> toName = Member::getUsername;
        Member minji = new Member("minji", 8500, 150, 5, 365);
        assertEquals("minji", toName.apply(minji));
    }

    @Test
    @DisplayName("정적 메서드 참조 — Integer::parseInt")
    void staticMethodReference() {
        Function<String, Integer> toInt = Integer::parseInt;
        assertEquals(42, toInt.apply("42"));
    }

    @Test
    @DisplayName("생성자 참조 — StringBuilder::new")
    void constructorReference() {
        Supplier<StringBuilder> make = StringBuilder::new;
        StringBuilder sb = make.get();
        sb.append("hi");
        assertEquals("hi", sb.toString());
    }
}
