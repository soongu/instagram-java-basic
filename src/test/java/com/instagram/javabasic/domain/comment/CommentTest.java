package com.instagram.javabasic.domain.comment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CommentTest {

    // ===== 생성 + getter =====

    @Test
    @DisplayName("생성: getter 로 작성자·내용·좋아요 수를 읽을 수 있다")
    void create_andRead() {
        Comment comment = new Comment("minji", "좋은 사진이에요", 3);

        assertEquals("minji", comment.getAuthor());
        assertEquals("좋은 사진이에요", comment.getText());
        assertEquals(3, comment.getLikeCount());
    }

    // ===== equals: 작성자 + 내용 기준 =====

    @Test
    @DisplayName("equals: 작성자와 내용이 같으면 좋아요 수가 달라도 같은 댓글로 본다")
    void equals_byAuthorAndText() {
        Comment a = new Comment("minji", "좋은 사진이에요", 3);
        Comment b = new Comment("minji", "좋은 사진이에요", 99);
        Comment c = new Comment("minji", "다른 내용", 3);

        assertTrue(a.equals(b));
        assertFalse(a.equals(c));
    }

    // ===== 불변: withLike 는 새 인스턴스를 반환하고 원본은 그대로다 =====

    @Test
    @DisplayName("불변: withLike 는 새 Comment 를 돌려주고 원본 좋아요 수는 그대로다")
    void withLike_returnsNewInstance_originalUnchanged() {
        Comment original = new Comment("minji", "좋은 사진이에요", 3);

        Comment liked = original.withLike();

        // 새 인스턴스다 (같은 객체가 아니다)
        assertNotSame(original, liked);
        // 새 인스턴스는 좋아요가 1 늘었다
        assertEquals(4, liked.getLikeCount());
        // 원본은 한 톨도 바뀌지 않았다
        assertEquals(3, original.getLikeCount());
    }
}
