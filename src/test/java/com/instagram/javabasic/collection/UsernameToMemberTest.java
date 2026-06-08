package com.instagram.javabasic.collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;

class UsernameToMemberTest {

    @Test
    @DisplayName("register 후 find 로 정확한 Member 를 돌려준다")
    void registerThenFind() {
        UsernameToMember repo = new UsernameToMember();
        Member minji = new Member("minji", 8500, 150, 12, 400);
        repo.register(minji);

        assertSame(minji, repo.find("minji"));
        assertEquals(1, repo.size());
    }

    @Test
    @DisplayName("없는 키는 find 시 null, exists 시 false 다")
    void missingKey() {
        UsernameToMember repo = new UsernameToMember();
        repo.register(new Member("minji", 8500, 150, 12, 400));

        assertNull(repo.find("unknown"));
        assertFalse(repo.exists("unknown"));
        assertTrue(repo.exists("minji"));
    }

    @Test
    @DisplayName("getOrDefault 는 없는 키일 때 기본 회원을 돌려준다")
    void getOrDefaultReturnsFallback() {
        UsernameToMember repo = new UsernameToMember();
        Member fallback = new Member("guest", 0, 0, 0, 0);

        Member result = repo.findOrDefault("unknown", fallback);
        assertSame(fallback, result);
    }
}
