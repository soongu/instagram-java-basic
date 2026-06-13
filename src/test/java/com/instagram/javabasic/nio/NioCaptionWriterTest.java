package com.instagram.javabasic.nio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class NioCaptionWriterTest {

    private final NioCaptionWriter writer = new NioCaptionWriter();

    @Test
    @DisplayName("writeWhole 은 기존 내용을 덮어쓴다")
    void writeWholeOverwrites(@TempDir Path tempDir) throws Exception {
        Path file = tempDir.resolve("caption.txt");
        writer.writeWhole(file, "처음 내용");
        writer.writeWhole(file, "새 내용");

        assertEquals("새 내용", Files.readString(file));
    }

    @Test
    @DisplayName("appendLine 은 기존 줄 뒤에 새 줄을 덧붙인다")
    void appendLineKeepsExisting(@TempDir Path tempDir) throws Exception {
        Path file = tempDir.resolve("comments.txt");
        writer.writeLines(file, List.of("좋아요!", "멋져요"));
        writer.appendLine(file, "팔로우했어요");

        assertEquals(List.of("좋아요!", "멋져요", "팔로우했어요"), Files.readAllLines(file));
    }
}
