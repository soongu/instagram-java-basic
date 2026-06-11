package com.instagram.javabasic.modern.solution.day28;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;

class Day28SolutionTest {

    // ===== 과제 1: MemberFinder =====

    @Test
    @DisplayName("과제1 findByUsername: 있으면 값이 든 상자, 없으면 빈 상자")
    void findByUsername() {
        List<Member> members = List.of(
                new Member("jaehoon", "jaehoon@example.com"),
                new Member("minji", 8500, 150, 5, 365));
        assertTrue(MemberFinder.findByUsername(members, "minji").isPresent());
        assertTrue(MemberFinder.findByUsername(members, "nobody").isEmpty());
    }

    @Test
    @DisplayName("과제1 getByUsernameOrThrow: 없으면 MemberNotFoundException")
    void getByUsernameOrThrow() {
        List<Member> members = List.of(new Member("jaehoon", "jaehoon@example.com"));
        assertEquals("jaehoon", MemberFinder.getByUsernameOrThrow(members, "jaehoon").getUsername());
        assertThrows(MemberNotFoundException.class,
                () -> MemberFinder.getByUsernameOrThrow(members, "nobody"));
    }

    // ===== 과제 2: AuthorGrade =====

    @Test
    @DisplayName("과제2 topAuthorGrade: 작성자 없으면 등급 없음")
    void gradeNoAuthor() {
        Post post = new Post("익명 글", "anon", 10); // 작성자 객체 없음
        assertEquals("등급 없음", AuthorGrade.topAuthorGrade(post));
    }

    @Test
    @DisplayName("과제2 topAuthorGrade: 팔로워 1000 미만이면 등급 없음")
    void gradeLowFollower() {
        Member newbie = new Member("newbie", 50, 0, 0, 10);
        Post post = new Post("첫 글", newbie, 3);
        assertEquals("등급 없음", AuthorGrade.topAuthorGrade(post));
    }

    @Test
    @DisplayName("과제2 topAuthorGrade: 팔로워 1000 이상이면 실제 등급")
    void gradeTopAuthor() {
        Member star = new Member("star", 30000, 500, 10, 730); // 점수 524 → 강력 추천
        Post post = new Post("일상", star, 250);
        assertEquals("강력 추천", AuthorGrade.topAuthorGrade(post));
    }

    // ===== 과제 3: DomainCollector =====

    @Test
    @DisplayName("과제3 registeredDomains: 이메일 등록자의 도메인만 중복 없이")
    void registeredDomains() {
        List<Member> members = List.of(
                new Member("jaehoon", "jaehoon@example.com"),
                new Member("minji", 8500, 150, 5, 365), // 이메일 null
                new Member("dana", "dana@gmail.com"),
                new Member("seungwoo", "seungwoo@example.com"),
                new Member("noat", "골뱅이없는주소")); // @ 없음
        assertEquals(List.of("example.com", "gmail.com"),
                DomainCollector.registeredDomains(members));
    }
}
