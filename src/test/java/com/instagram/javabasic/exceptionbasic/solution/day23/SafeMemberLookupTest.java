package com.instagram.javabasic.exceptionbasic.solution.day23;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;
import com.instagram.javabasic.exceptionbasic.MemberRepository;

class SafeMemberLookupTest {

    private SafeMemberLookup newLookupWithMinji() {
        MemberRepository repo = new MemberRepository();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        return new SafeMemberLookup(repo);
    }

    @Test
    @DisplayName("있는 id 는 회원을 그대로 돌려준다")
    void returnsMemberWhenFound() {
        SafeMemberLookup lookup = newLookupWithMinji();

        Member found = lookup.getMemberSafely(1L);

        assertEquals("minji", found.getUsername());
    }

    @Test
    @DisplayName("없는 id 는 MemberNotFoundException 으로 재포장돼 던져진다")
    void throwsMemberNotFoundWhenMissing() {
        SafeMemberLookup lookup = newLookupWithMinji();

        assertThrows(MemberNotFoundException.class,
                () -> lookup.getMemberSafely(99L));
    }

    @Test
    @DisplayName("재포장된 예외의 cause 는 저장소의 IllegalArgumentException 이다")
    void preservesCauseAsIllegalArgument() {
        SafeMemberLookup lookup = newLookupWithMinji();

        MemberNotFoundException thrown = assertThrows(
                MemberNotFoundException.class,
                () -> lookup.getMemberSafely(99L));

        assertInstanceOf(IllegalArgumentException.class, thrown.getCause());
    }

    @Test
    @DisplayName("겉 메시지와 원인 메시지가 둘 다 살아 있다")
    void keepsBothMessages() {
        SafeMemberLookup lookup = newLookupWithMinji();

        MemberNotFoundException thrown = assertThrows(
                MemberNotFoundException.class,
                () -> lookup.getMemberSafely(99L));

        assertEquals("회원 id 99 를 찾을 수 없어요", thrown.getMessage());
        assertEquals("id 99 에 해당하는 회원이 없어요.", thrown.getCause().getMessage());
    }
}
