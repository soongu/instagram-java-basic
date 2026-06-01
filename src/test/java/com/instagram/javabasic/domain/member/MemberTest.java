package com.instagram.javabasic.domain.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberTest {

    @Test
    @DisplayName("기본 생성자로 만든 객체는 필드가 기본값(null/0)이다")
    void defaultConstructor_initializesFieldsToDefaults() {
        Member member = new Member();

        assertNull(member.username);
        assertEquals(0, member.followers);
        assertEquals(0, member.posts);
        assertEquals(0, member.mutualFriends);
        assertEquals(0, member.daysActive);
    }

    @Test
    @DisplayName("매개변수 생성자로 만든 객체는 전달한 값이 필드에 그대로 담긴다")
    void parameterizedConstructor_assignsAllFields() {
        Member member = new Member("jaehoon_dev", 1240, 42, 8, 120);

        assertEquals("jaehoon_dev", member.username);
        assertEquals(1240, member.followers);
        assertEquals(42, member.posts);
        assertEquals(8, member.mutualFriends);
        assertEquals(120, member.daysActive);
    }
}
