package com.instagram.javabasic.nio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class UploadFolderScannerTest {

    private final UploadFolderScanner scanner = new UploadFolderScanner();

    @Test
    @DisplayName("list 는 폴더 바로 아래 항목만 센다")
    void listsDirectChildrenOnly(@TempDir Path tempDir) throws Exception {
        Files.createDirectories(tempDir.resolve("jaehoon"));
        Files.writeString(tempDir.resolve("readme.txt"), "x");

        assertEquals(2, scanner.listDirectly(tempDir).size());
    }

    @Test
    @DisplayName("walk 는 하위 폴더 속 .jpg 까지 재귀로 센다")
    void countsJpgRecursively(@TempDir Path tempDir) throws Exception {
        Files.createDirectories(tempDir.resolve("jaehoon"));
        Files.createDirectories(tempDir.resolve("minji"));
        Files.writeString(tempDir.resolve("jaehoon/a.jpg"), "x");
        Files.writeString(tempDir.resolve("jaehoon/b.jpg"), "x");
        Files.writeString(tempDir.resolve("minji/c.jpg"), "x");
        Files.writeString(tempDir.resolve("minji/memo.txt"), "x");

        assertEquals(3, scanner.countJpgRecursively(tempDir));
    }
}
