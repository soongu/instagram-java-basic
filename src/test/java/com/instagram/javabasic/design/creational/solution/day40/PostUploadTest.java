package com.instagram.javabasic.design.creational.solution.day40;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostUploadTest {

    @Test
    @DisplayName("caption 만 넣으면 선택 항목은 기본값으로 채워진다")
    void requiredOnly() {
        PostUpload upload = PostUpload.builder("첫 게시물").build();

        assertEquals("첫 게시물", upload.getCaption());
        assertEquals("", upload.getLocation());
        assertTrue(upload.isCommentsEnabled());   // 기본: 댓글 허용
        assertFalse(upload.isHideLikeCount());     // 기본: 좋아요 수 보임
    }

    @Test
    @DisplayName("선택 옵션을 이름표 붙여 이어서 채울 수 있다")
    void withOptions() {
        PostUpload upload = PostUpload.builder("제주 여행")
                .location("제주도")
                .hideLikeCount(true)
                .commentsEnabled(false)
                .build();

        assertEquals("제주 여행", upload.getCaption());
        assertEquals("제주도", upload.getLocation());
        assertTrue(upload.isHideLikeCount());
        assertFalse(upload.isCommentsEnabled());
    }
}
