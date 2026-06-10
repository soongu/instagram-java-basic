package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.function.Consumer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class FunctionConsumerSupplierTest {

    @Test
    @DisplayName("Function.apply — 회원을 이름표 문자열로 바꾼다")
    void functionTransforms() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        assertEquals("@minji", FunctionConsumerSupplier.TO_TAG.apply(minji));
    }

    @Test
    @DisplayName("Supplier.get — 부를 때마다 새 게스트 객체를 만든다")
    void supplierCreatesFresh() {
        Member first = FunctionConsumerSupplier.GUEST.get();
        Member second = FunctionConsumerSupplier.GUEST.get();
        assertEquals("guest", first.getUsername());
        assertNotSame(first, second);
    }

    @Test
    @DisplayName("Consumer.accept — 받은 값을 소비한다(돌려주는 값 없음)")
    void consumerAccepts() {
        StringBuilder log = new StringBuilder();
        Consumer<String> collector = line -> log.append(line);
        collector.accept("a");
        collector.accept("b");
        assertEquals("ab", log.toString());
    }
}
