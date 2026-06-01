package com.instagram.javabasic.domain.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostTest {

    @Test
    @DisplayName("toString 오버라이딩: 사람 친화적인 형식으로 출력된다")
    void toString_formatsPost() {
        Post post = new Post("오늘 점심 맛집 추천!", "jaehoon_dev", 12);
        assertEquals("[게시물] 내용: 오늘 점심 맛집 추천!, 좋아요: 12", post.toString());
    }

    @Test
    @DisplayName("equals: 작성자와 내용이 모두 같으면 같은 게시물이다")
    void equals_trueWhenSameAuthorAndContent() {
        Post a = new Post("산책 다녀왔어요", "minji", 3);
        Post b = new Post("산책 다녀왔어요", "minji", 99);
        assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("equals: 내용이 다르면 다른 게시물이다")
    void equals_falseWhenDifferentContent() {
        Post a = new Post("산책 다녀왔어요", "minji", 3);
        Post b = new Post("운동 다녀왔어요", "minji", 3);
        assertFalse(a.equals(b));
    }

    @Test
    @DisplayName("equals: 자기 자신과 비교하면 true (this == obj)")
    void equals_trueWhenSameReference() {
        Post a = new Post("산책 다녀왔어요", "minji", 3);
        assertTrue(a.equals(a));
    }

    @Test
    @DisplayName("equals: null 과 비교하면 false")
    void equals_falseWhenNull() {
        Post a = new Post("산책 다녀왔어요", "minji", 3);
        assertFalse(a.equals(null));
    }

    @Test
    @DisplayName("addLike 후에도 작성자+내용이 같으면 equals 는 유지된다")
    void equals_unaffectedByLikeCount() {
        Post a = new Post("산책 다녀왔어요", "minji", 3);
        Post b = new Post("산책 다녀왔어요", "minji", 3);
        a.addLike();
        a.addLike();
        assertEquals(5, a.getLikeCount());
        assertTrue(a.equals(b));
    }

    @Test
    @DisplayName("기본 생성자 + getter: 빈 게시물을 만들 수 있다")
    void defaultConstructor_works() {
        Post post = new Post();
        post.addLike();
        assertEquals(1, post.getLikeCount());
    }
}
