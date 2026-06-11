package com.instagram.javabasic.modern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

class OptionalFlatMapDemoTest {

    private final Member jaehoon = new Member("jaehoon", "jaehoon@example.com");
    private final Member minji = new Member("minji", 8500, 150, 5, 365); // 이메일 null

    @Test
    @DisplayName("flatMap: 작성자 이메일이 있으면 그 이메일")
    void authorEmailPresent() {
        Post withEmail = new Post("카페 다녀옴", jaehoon, 250);
        assertEquals("jaehoon@example.com",
                OptionalFlatMapDemo.authorEmailOrDefault(withEmail));
    }

    @Test
    @DisplayName("flatMap: 작성자는 있지만 이메일이 없으면 기본값")
    void authorNoEmail() {
        Post post = new Post("산책", minji, 40);
        assertEquals("(작성자 이메일 없음)",
                OptionalFlatMapDemo.authorEmailOrDefault(post));
    }

    @Test
    @DisplayName("flatMap: 작성자 객체 자체가 없어도 NPE 없이 기본값")
    void noAuthor() {
        Post post = new Post("익명 글", "anon", 10); // 작성자 객체 없음
        assertEquals("(작성자 이메일 없음)",
                OptionalFlatMapDemo.authorEmailOrDefault(post));
    }

    @Test
    @DisplayName("filter: 좋아요 100+ 면 통과")
    void popularPass() {
        Post popular = new Post("카페 다녀옴", jaehoon, 250);
        assertTrue(OptionalFlatMapDemo.popularOnly(popular).isPresent());
    }

    @Test
    @DisplayName("filter: 좋아요 100 미만이면 빈 상자")
    void popularBlocked() {
        Post quiet = new Post("산책", minji, 40);
        assertFalse(OptionalFlatMapDemo.popularOnly(quiet).isPresent());
    }
}
