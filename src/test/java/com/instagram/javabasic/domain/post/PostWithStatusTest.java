package com.instagram.javabasic.domain.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostWithStatusTest {

    @Test
    @DisplayName("새 게시물의 상태는 기본이 공개(PUBLIC)다")
    void newPost_defaultsToPublic() {
        Post post = new Post("첫 게시물!", "jaehoon_dev", 0);
        assertEquals(PostStatus.PUBLIC, post.getStatus());
    }

    @Test
    @DisplayName("setStatus: 상태를 보관됨으로 바꿀 수 있다")
    void setStatus_changesStatus() {
        Post post = new Post("옛날 사진", "minji", 10);
        post.setStatus(PostStatus.ARCHIVED);
        assertEquals(PostStatus.ARCHIVED, post.getStatus());
    }

    @Test
    @DisplayName("canBeShared: 판단을 상태(enum)에게 위임한다")
    void canBeShared_delegatesToStatus() {
        Post post = new Post("공유 가능한 글", "seungwoo", 5);
        assertTrue(post.canBeShared());

        post.setStatus(PostStatus.PRIVATE);
        assertFalse(post.canBeShared());
    }
}
