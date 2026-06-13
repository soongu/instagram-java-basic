package com.instagram.javabasic.nio.solution.day39;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class MediaCounterTest {

    private final MediaCounter counter = new MediaCounter();

    @Test
    @DisplayName("하위 폴더까지 재귀로 확장자별 개수를 따로 센다")
    void countsEachExtensionRecursively(@TempDir Path tempDir) throws Exception {
        Files.createDirectories(tempDir.resolve("jaehoon"));
        Files.createDirectories(tempDir.resolve("minji"));
        Files.writeString(tempDir.resolve("jaehoon/a.jpg"), "x");
        Files.writeString(tempDir.resolve("jaehoon/b.jpg"), "x");
        Files.writeString(tempDir.resolve("jaehoon/clip.mp4"), "x");
        Files.writeString(tempDir.resolve("minji/c.jpg"), "x");
        Files.writeString(tempDir.resolve("minji/reel.mp4"), "x");

        assertEquals(3, counter.countByExtension(tempDir, ".jpg"));
        assertEquals(2, counter.countByExtension(tempDir, ".mp4"));
    }

    @Test
    @DisplayName("해당 확장자가 없으면 0을 센다")
    void countsZeroWhenNoMatch(@TempDir Path tempDir) throws Exception {
        Files.writeString(tempDir.resolve("memo.txt"), "x");

        assertEquals(0, counter.countByExtension(tempDir, ".jpg"));
    }
}
