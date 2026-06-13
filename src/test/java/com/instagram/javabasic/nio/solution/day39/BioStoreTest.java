package com.instagram.javabasic.nio.solution.day39;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class BioStoreTest {

    private final BioStore store = new BioStore();

    @Test
    @DisplayName("소개글을 저장하고 불러오면 한글·이모지가 그대로다")
    void roundTripPreservesKoreanAndEmoji(@TempDir Path tempDir) throws Exception {
        Path file = tempDir.resolve("bio.txt");
        String bio = "사진 찍는 걸 좋아해요 📷";

        store.save(file, bio);
        String loaded = store.load(file);

        assertEquals(bio, loaded);
    }

    @Test
    @DisplayName("save 는 기존 소개글을 새 내용으로 덮어쓴다")
    void saveOverwritesPreviousBio(@TempDir Path tempDir) throws Exception {
        Path file = tempDir.resolve("bio.txt");
        store.save(file, "예전 소개글");
        store.save(file, "새 소개글");

        assertEquals("새 소개글", store.load(file));
    }
}
