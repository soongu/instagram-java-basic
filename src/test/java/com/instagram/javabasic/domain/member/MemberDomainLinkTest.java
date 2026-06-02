package com.instagram.javabasic.domain.member;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.instagram.javabasic.domain.post.Post;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MemberDomainLinkTest {

    @Test
    @DisplayName("addWrittenPost: 작성한 글을 배열에 모으고 개수가 늘어난다")
    void addWrittenPost_storesAndCounts() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        Post p1 = new Post("첫 글", jaehoon, 0);
        Post p2 = new Post("둘째 글", jaehoon, 0);

        jaehoon.addWrittenPost(p1);
        jaehoon.addWrittenPost(p2);

        assertEquals(2, jaehoon.getWrittenPostCount());
        assertSame(p1, jaehoon.getWrittenPost(0));
        assertSame(p2, jaehoon.getWrittenPost(1));
    }

    @Test
    @DisplayName("follow: 다른 회원을 팔로잉 묶음에 추가하고 개수가 늘어난다")
    void follow_storesAndCounts() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        Member minji = new Member("minji_cafe", 8500, 150, 23, 365);
        Member seungwoo = new Member("seungwoo", 320, 12, 2, 30);

        jaehoon.follow(minji);
        jaehoon.follow(seungwoo);

        assertEquals(2, jaehoon.getFollowingCount());
        assertSame(minji, jaehoon.getFollowing(0));
        assertSame(seungwoo, jaehoon.getFollowing(1));
    }

    @Test
    @DisplayName("isFollowing: 팔로우한 상대는 true, 안 한 상대는 false (equals 활용)")
    void isFollowing_usesEquals() {
        Member jaehoon = new Member("jaehoon_dev", 1240, 42, 8, 120);
        Member minji = new Member("minji_cafe", 8500, 150, 23, 365);
        Member seungwoo = new Member("seungwoo", 320, 12, 2, 30);

        jaehoon.follow(minji);

        assertTrue(jaehoon.isFollowing(minji));
        // username 만 같아도 같은 사람으로 본다 (Member.equals 는 username 기준)
        assertTrue(jaehoon.isFollowing(new Member("minji_cafe", 1, 1, 1, 1)));
        assertFalse(jaehoon.isFollowing(seungwoo));
    }

    @Test
    @DisplayName("기본 생성자로 만든 객체도 묶음이 비어있는 상태로 시작한다 (가법 진화)")
    void defaultConstructor_initializesEmptyCollections() {
        Member member = new Member();
        assertEquals(0, member.getWrittenPostCount());
        assertEquals(0, member.getFollowingCount());
    }
}
