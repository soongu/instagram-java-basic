package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;

class OptionalRepositoryDemoTest {

    private OptionalRepositoryDemo repo;

    @BeforeEach
    void setUp() {
        repo = new OptionalRepositoryDemo();
        repo.save(1L, new Member("jaehoon", "jaehoon@example.com"));
        repo.save(2L, new Member("minji", 8500, 150, 5, 365));
    }

    @Test
    @DisplayName("findById: 있으면 값이 든 상자, 없으면 빈 상자")
    void findById() {
        assertTrue(repo.findById(1L).isPresent());
        assertFalse(repo.findById(99L).isPresent());
    }

    @Test
    @DisplayName("호출자 A(orElseThrow): 없으면 MemberNotFoundException")
    void getOrThrow() {
        assertEquals("jaehoon", repo.getOrThrow(1L).getUsername());
        assertThrows(MemberNotFoundException.class, () -> repo.getOrThrow(99L));
    }

    @Test
    @DisplayName("호출자 B(orElse): 없으면 게스트 — 같은 findById, 다른 정책")
    void usernameOrGuest() {
        assertEquals("jaehoon", repo.usernameOrGuest(1L));
        assertEquals("게스트", repo.usernameOrGuest(99L));
    }

    @Test
    @DisplayName("Optional + Stream: 존재하는 회원만 모으기")
    void findExisting() {
        List<Member> found = repo.findExisting(List.of(1L, 99L, 2L));
        assertEquals(2, found.size());
    }
}
