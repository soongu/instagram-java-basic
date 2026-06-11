package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class StreamCollectComprehensiveTest {

    @Test
    @DisplayName("좋아요 200+ 글을 작성자별로 세서 상위 2명을 쉼표로 연결")
    void topAuthorsByPopularPosts() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        Member seungwoo = new Member("seungwoo", 320, 12, 2, 60);
        List<Post> posts = List.of(
                new Post("카페", minji, 250),
                new Post("여행", minji, 300),
                new Post("코딩", jaehoon, 210),
                new Post("산책", seungwoo, 50));

        // minji 2개, jaehoon 1개 → 상위 2명: "minji, jaehoon" (seungwoo 는 200 미만이라 제외)
        assertEquals("minji, jaehoon", StreamCollectComprehensive.topAuthorsByPopularPosts(posts, 2));
    }

    @Test
    @DisplayName("limit으로 상위 1명만 추리면 minji")
    void topOne() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        List<Post> posts = List.of(
                new Post("카페", minji, 250),
                new Post("여행", minji, 300),
                new Post("코딩", jaehoon, 210));

        assertEquals("minji", StreamCollectComprehensive.topAuthorsByPopularPosts(posts, 1));
    }
}
