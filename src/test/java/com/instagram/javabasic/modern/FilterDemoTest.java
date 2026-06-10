package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

class FilterDemoTest {

    private List<Post> sample() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Post secret = new Post("비밀 인기 글", minji, 300);
        secret.setStatus(PostStatus.PRIVATE);
        return List.of(
                new Post("인기 공개 글", minji, 250),
                secret,
                new Post("평범한 글", minji, 40));
    }

    @Test
    @DisplayName("인기 글(좋아요 100+)은 공개/비공개 상관없이 2개")
    void popularPosts() {
        List<Post> popular = FilterDemo.popularPosts(sample());
        assertEquals(2, popular.size());
        for (Post p : popular) {
            assertTrue(p.getLikeCount() >= 100);
        }
    }

    @Test
    @DisplayName("filter 두 번이면 공개이면서 인기인 글만 1개")
    void publicAndPopular() {
        assertEquals(1, FilterDemo.publicAndPopular(sample()).size());
    }
}
