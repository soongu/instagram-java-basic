package com.instagram.javabasic.domain.post;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PostStatusTest {

    @Test
    @DisplayName("displayName: 상태마다 한글 이름을 갖는다")
    void displayName_perStatus() {
        assertEquals("공개", PostStatus.PUBLIC.getDisplayName());
        assertEquals("비공개", PostStatus.PRIVATE.getDisplayName());
        assertEquals("보관됨", PostStatus.ARCHIVED.getDisplayName());
    }

    @Test
    @DisplayName("canShare: 공개만 공유 가능하다")
    void canShare_onlyPublic() {
        assertTrue(PostStatus.PUBLIC.canShare());
        assertFalse(PostStatus.PRIVATE.canShare());
        assertFalse(PostStatus.ARCHIVED.canShare());
    }

    @Test
    @DisplayName("isEditable: 보관된 글만 수정 불가다")
    void isEditable_exceptArchived() {
        assertTrue(PostStatus.PUBLIC.isEditable());
        assertTrue(PostStatus.PRIVATE.isEditable());
        assertFalse(PostStatus.ARCHIVED.isEditable());
    }
}
