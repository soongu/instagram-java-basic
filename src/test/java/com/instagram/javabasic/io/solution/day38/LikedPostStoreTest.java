package com.instagram.javabasic.io.solution.day38;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class LikedPostStoreTest {

    private final LikedPostStore store = new LikedPostStore();

    @Test
    @DisplayName("좋아요 수를 포함한 게시물을 저장하고 불러오면 네 필드가 모두 복원된다")
    void roundTripRestoresAllFourFields(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "liked-posts.txt");
        LikedPost first = new LikedPost(1L, "jaehoon", "첫 게시물이에요", 12);
        LikedPost second = new LikedPost(2L, "minji", "오늘 날씨 좋네요", 340);

        store.saveAll(file, List.of(first, second));
        List<LikedPost> loaded = store.loadAll(file);

        assertEquals(2, loaded.size());
        assertEquals(first, loaded.get(0));
        assertEquals(second, loaded.get(1));
        assertEquals(12, loaded.get(0).likes());
        assertEquals(340, loaded.get(1).likes());
    }

    @Test
    @DisplayName("게시물이 없으면 빈 목록으로 복원된다")
    void emptyListRoundTrips(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "empty.txt");

        store.saveAll(file, List.of());
        List<LikedPost> loaded = store.loadAll(file);

        assertEquals(0, loaded.size());
    }
}
