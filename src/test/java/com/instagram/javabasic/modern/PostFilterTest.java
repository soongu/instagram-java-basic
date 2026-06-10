package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class PostFilterTest {

    private List<Post> sample(Member author) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("첫 글", author, 30));
        posts.add(new Post("인기 글", author, 250));
        posts.add(new Post("보통 글", author, 120));
        return posts;
    }

    @Test
    @DisplayName("좋아요 100 이상만 거른다 — 조건을 람다로 넘긴다")
    void filterByLikeCount() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> popular = PostFilter.filter(sample(minji), p -> p.getLikeCount() >= 100);
        assertEquals(2, popular.size());
        for (Post p : popular) {
            assertTrue(p.getLikeCount() >= 100);
        }
    }

    @Test
    @DisplayName("같은 filter 에 다른 조건을 넘기면 다른 결과 — 특정 작성자 글만")
    void filterByAuthor() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        List<Post> posts = sample(minji);
        posts.add(new Post("재훈 글", jaehoon, 10));

        List<Post> mine = PostFilter.filter(posts, p -> minji.equals(p.getAuthor()));
        assertEquals(3, mine.size());
    }

    @Test
    @DisplayName("원본 리스트는 건드리지 않는다")
    void doesNotMutateInput() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> posts = sample(minji);
        PostFilter.filter(posts, p -> p.getLikeCount() >= 100);
        assertEquals(3, posts.size());
    }
}
