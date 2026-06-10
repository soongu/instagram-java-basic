package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class SortedDistinctDemoTest {

    private List<Post> sample() {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        return List.of(
                new Post("글 A", minji, 80),
                new Post("글 B", jaehoon, 250),
                new Post("글 C", minji, 120));
    }

    @Test
    @DisplayName("좋아요 내림차순 정렬 — 맨 앞이 가장 많은 250")
    void byLikesDesc() {
        List<Post> sorted = SortedDistinctDemo.byLikesDesc(sample());
        assertEquals(250, sorted.get(0).getLikeCount());
        assertEquals(80, sorted.get(2).getLikeCount());
    }

    @Test
    @DisplayName("작성자 중복 제거 — minji 가 두 글을 써도 한 번만")
    void distinctAuthors() {
        assertEquals(List.of("minji", "jaehoon"), SortedDistinctDemo.distinctAuthors(sample()));
    }

    @Test
    @DisplayName("중복 제거 후 가나다순 정렬")
    void sortedDistinctAuthors() {
        assertEquals(List.of("jaehoon", "minji"), SortedDistinctDemo.sortedDistinctAuthors(sample()));
    }
}
