package com.instagram.javabasic.nio.solution.day39;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class FolderBackupTest {

    private final FolderBackup backup = new FolderBackup();

    @Test
    @DisplayName("하위 폴더 구조와 파일 내용이 백업 폴더에 그대로 복사된다")
    void copiesStructureAndContent(@TempDir Path tempDir) throws Exception {
        Path source = tempDir.resolve("uploads");
        Path target = tempDir.resolve("backup");
        Files.createDirectories(source.resolve("jaehoon"));
        Files.writeString(source.resolve("jaehoon/post1.jpg"), "사진 데이터");
        Files.writeString(source.resolve("readme.txt"), "안내문");

        backup.backup(source, target);

        assertTrue(Files.exists(target.resolve("jaehoon/post1.jpg")));
        assertEquals("사진 데이터", Files.readString(target.resolve("jaehoon/post1.jpg")));
        assertEquals("안내문", Files.readString(target.resolve("readme.txt")));
    }

    @Test
    @DisplayName("다시 백업해도 덮어쓰며 예외 없이 끝난다")
    void backupIsRepeatable(@TempDir Path tempDir) throws Exception {
        Path source = tempDir.resolve("uploads");
        Path target = tempDir.resolve("backup");
        Files.createDirectories(source);
        Files.writeString(source.resolve("a.jpg"), "처음");

        backup.backup(source, target);
        Files.writeString(source.resolve("a.jpg"), "수정됨");
        backup.backup(source, target);

        assertEquals("수정됨", Files.readString(target.resolve("a.jpg")));
    }
}
