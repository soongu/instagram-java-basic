package com.instagram.javabasic.nio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class NioCaptionReaderTest {

    private final NioCaptionReader reader = new NioCaptionReader();

    @Test
    @DisplayName("readString 으로 파일 전체를 한 번에 읽고 한글이 깨지지 않는다")
    void readsWholeFileWithKorean(@TempDir Path tempDir) throws Exception {
        Path file = tempDir.resolve("caption.txt");
        Files.writeString(file, "오늘 노을 최고");

        assertEquals("오늘 노을 최고", reader.readWhole(file));
    }

    @Test
    @DisplayName("readAllLines 로 줄 리스트를 읽는다")
    void readsLinesAsList(@TempDir Path tempDir) throws Exception {
        Path file = tempDir.resolve("lines.txt");
        Files.write(file, List.of("첫 줄", "둘째 줄"));

        assertEquals(List.of("첫 줄", "둘째 줄"), reader.readLines(file));
    }

    @Test
    @DisplayName("lines 로 빈 줄을 뺀 줄 수를 센다")
    void countsNonEmptyLines(@TempDir Path tempDir) throws Exception {
        Path file = tempDir.resolve("feed.txt");
        Files.writeString(file, "노을\n\n한강\n");

        assertEquals(2, reader.countNonEmptyLines(file));
    }
}
