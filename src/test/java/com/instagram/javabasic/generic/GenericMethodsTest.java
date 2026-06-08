package com.instagram.javabasic.generic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class GenericMethodsTest {

    @Test
    @DisplayName("first 는 String 리스트의 첫 원소를 형변환 없이 돌려준다")
    void firstOnStringList() {
        List<String> names = new ArrayList<>();
        names.add("minji");
        names.add("jaehoon");

        assertEquals("minji", GenericMethods.first(names));
    }

    @Test
    @DisplayName("first 는 Member 리스트에서도 같은 메서드로 동작한다")
    void firstOnMemberList() {
        Member minji = new Member("minji", 8500, 150, 12, 400);
        List<Member> members = new ArrayList<>();
        members.add(minji);

        assertSame(minji, GenericMethods.first(members));
    }

    @Test
    @DisplayName("빈 리스트에 first 를 부르면 IllegalArgumentException 이 터진다")
    void firstOnEmptyListThrows() {
        List<String> empty = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> GenericMethods.first(empty));
    }

    @Test
    @DisplayName("swap 은 두 자리의 원소를 맞바꾼다")
    void swapExchangesElements() {
        List<String> names = new ArrayList<>();
        names.add("minji");
        names.add("jaehoon");
        names.add("seungwoo");

        GenericMethods.swap(names, 0, 2);

        assertEquals("seungwoo", names.get(0));
        assertEquals("minji", names.get(2));
    }
}
