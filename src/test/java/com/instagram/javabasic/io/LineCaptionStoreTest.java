package com.instagram.javabasic.io;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class LineCaptionStoreTest {

    private final LineCaptionStore store = new LineCaptionStore();

    @Test
    @DisplayName("여러 줄을 쓴 순서와 개수 그대로 다시 읽어온다")
    void preservesLineOrderAndCount(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "captions.txt");
        List<String> lines = List.of("첫 줄", "둘째 줄", "셋째 줄");

        store.writeLines(file, lines);
        List<String> loaded = store.readLines(file);

        assertEquals(3, loaded.size());
        assertEquals(lines, loaded);
    }

    @Test
    @DisplayName("빈 리스트를 쓰면 읽을 줄이 하나도 없다")
    void emptyListProducesNoLines(@TempDir File tempDir) throws Exception {
        File file = new File(tempDir, "empty-lines.txt");

        store.writeLines(file, List.of());
        List<String> loaded = store.readLines(file);

        assertTrue(loaded.isEmpty());
    }
}
