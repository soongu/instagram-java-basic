package com.instagram.javabasic.generic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class BoxTest {

    @Test
    @DisplayName("Box<String> 에서 형변환 없이 String 을 꺼낸다")
    void stringBoxReturnsStringWithoutCast() {
        Box<String> box = new Box<>();
        box.set("minji");

        String value = box.get();

        assertEquals("minji", value);
    }

    @Test
    @DisplayName("Box<Member> 에서 형변환 없이 Member 를 꺼낸다")
    void memberBoxReturnsMemberWithoutCast() {
        Member minji = new Member("minji", 8500, 150, 12, 400);
        Box<Member> box = new Box<>();
        box.set(minji);

        Member value = box.get();

        assertSame(minji, value);
        assertEquals("minji", value.getUsername());
    }

    @Test
    @DisplayName("set 으로 다시 담으면 마지막에 넣은 값이 나온다")
    void setOverwritesPreviousValue() {
        Box<Integer> box = new Box<>();
        box.set(100);
        box.set(8500);

        assertEquals(8500, box.get());
    }
}
