package com.instagram.javabasic.modern.solution.day25;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class Day25SolutionTest {

    private List<Member> members() {
        List<Member> members = new ArrayList<>();
        members.add(new Member("minji", 8500, 150, 5, 365));
        members.add(new Member("jaehoon", 1240, 42, 3, 200));
        members.add(new Member("newbie", 50, 2, 10, 5));
        members.add(new Member("seungwoo", 320, 12, 1, 90));
        return members;
    }

    @Test
    @DisplayName("과제1 MemberSelector — 같은 select 에 람다만 바꿔 다른 결과")
    void memberSelector() {
        List<Member> ms = members();
        assertEquals(2, MemberSelector.select(ms, m -> m.getFollowers() >= 1000).size());
        assertEquals(2, MemberSelector.select(ms, m -> m.getDaysActive() < 100).size());
        assertEquals(3, MemberSelector.select(ms, m -> m.calculateRecommendScore() >= 50).size());
    }

    @Test
    @DisplayName("과제2 NotificationFormatter — Function 으로 게시물을 알림 문구로 변환")
    void notificationFormatter() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Post post = new Post("첫 글이에요", minji, 30);
        String message = NotificationFormatter.TO_MESSAGE.apply(post);
        assertEquals("[알림] @minji 님의 새 글: 첫 글이에요 (좋아요 30)", message);
    }

    @Test
    @DisplayName("과제3 Filters — 제네릭 filter 가 회원에도 게시물에도 통한다")
    void genericFilter() {
        List<Member> ms = members();
        assertEquals(2, Filters.filter(ms, m -> m.getFollowers() >= 1000).size());

        Member author = ms.get(0);
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("a", author, 30));
        posts.add(new Post("b", author, 250));
        assertEquals(1, Filters.filter(posts, p -> p.getLikeCount() >= 100).size());
    }
}
