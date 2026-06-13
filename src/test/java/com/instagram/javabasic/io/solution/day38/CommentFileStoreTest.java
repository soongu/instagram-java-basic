package com.instagram.javabasic.io.solution.day38;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class CommentFileStoreTest {

    private final CommentFileStore store = new CommentFileStore();

    @Test
    @DisplayName("댓글 3개를 저장하고 불러오면 개수와 내용이 그대로다")
    void roundTripPreservesCommentCountAndContent(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "comments.txt");
        List<String> comments = List.of("좋아요!", "사진 멋져요 🌙", "팔로우했어요");

        store.saveComments(file, comments);
        List<String> loaded = store.loadComments(file);

        assertEquals(3, loaded.size());
        assertEquals(comments, loaded);
    }

    @Test
    @DisplayName("한글 댓글이 UTF-8 로 깨지지 않고 복원된다")
    void koreanCommentRoundTrips(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "korean.txt");
        List<String> comments = List.of("안녕하세요 반갑습니다");

        store.saveComments(file, comments);
        List<String> loaded = store.loadComments(file);

        assertEquals("안녕하세요 반갑습니다", loaded.get(0));
    }
}
