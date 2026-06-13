package com.instagram.javabasic.io;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class PostTextStoreTest {

    private final PostTextStore store = new PostTextStore();

    @Test
    @DisplayName("게시물 목록을 텍스트로 저장하고 다시 불러오면 id·작성자·캡션이 그대로 복원된다")
    void roundTripRestoresPostFields(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "posts.txt");
        Post first = new Post(1L, "jaehoon", "첫 게시물이에요");
        Post second = new Post(2L, "minji", "오늘 날씨 좋네요");

        store.saveAll(file, List.of(first, second));
        List<Post> loaded = store.loadAll(file);

        assertEquals(2, loaded.size());
        assertEquals(1L, loaded.get(0).id());
        assertEquals("jaehoon", loaded.get(0).author());
        assertEquals("첫 게시물이에요", loaded.get(0).caption());
        assertEquals(2L, loaded.get(1).id());
        assertEquals("minji", loaded.get(1).author());
        assertEquals("오늘 날씨 좋네요", loaded.get(1).caption());
    }

    @Test
    @DisplayName("게시물이 없으면 빈 목록으로 복원된다")
    void emptyListRoundTrips(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "empty-posts.txt");

        store.saveAll(file, List.of());
        List<Post> loaded = store.loadAll(file);

        assertEquals(0, loaded.size());
    }
}
