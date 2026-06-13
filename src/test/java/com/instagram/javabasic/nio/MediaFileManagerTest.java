package com.instagram.javabasic.nio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class MediaFileManagerTest {

    private final MediaFileManager manager = new MediaFileManager();

    @Test
    @DisplayName("createDirectories 는 중간 폴더까지 한 번에 만든다")
    void ensureDirCreatesNestedFolders(@TempDir Path tempDir) throws Exception {
        Path nested = tempDir.resolve("uploads/jaehoon/2026");
        manager.ensureDir(nested);

        assertTrue(manager.exists(nested));
    }

    @Test
    @DisplayName("copy 는 원본을 두고 사본을 만든다")
    void copyKeepsOriginal(@TempDir Path tempDir) throws Exception {
        Path original = tempDir.resolve("post1.jpg");
        Files.writeString(original, "사진 데이터");
        Path thumbnail = tempDir.resolve("post1-thumb.jpg");

        manager.copyFile(original, thumbnail);

        assertTrue(manager.exists(original));
        assertTrue(manager.exists(thumbnail));
    }

    @Test
    @DisplayName("move 는 원본을 옮겨 원래 자리에서 사라진다")
    void moveRemovesSource(@TempDir Path tempDir) throws Exception {
        Path temp = tempDir.resolve("temp.jpg");
        Files.writeString(temp, "사진");
        Path permanent = tempDir.resolve("permanent.jpg");

        manager.moveFile(temp, permanent);

        assertFalse(manager.exists(temp));
        assertTrue(manager.exists(permanent));
    }

    @Test
    @DisplayName("deleteIfExists 는 없는 파일에도 예외 없이 false 를 준다")
    void deleteIfExistsIsSafe(@TempDir Path tempDir) throws Exception {
        Path missing = tempDir.resolve("none.jpg");

        assertFalse(manager.removeIfExists(missing));
    }

    @Test
    @DisplayName("size 는 파일의 바이트 용량을 잰다")
    void sizeMeasuresBytes(@TempDir Path tempDir) throws Exception {
        Path file = tempDir.resolve("ascii.txt");
        Files.writeString(file, "abcde");

        assertEquals(5, manager.fileSize(file));
    }
}
