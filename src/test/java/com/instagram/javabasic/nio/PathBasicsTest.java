package com.instagram.javabasic.nio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PathBasicsTest {

    @Test
    @DisplayName("resolve 로 이은 경로의 파일 이름·부모·깊이가 맞다")
    void resolveBuildsPathParts() {
        Path photo = Path.of("uploads").resolve("jaehoon").resolve("post1.jpg");

        assertEquals("post1.jpg", photo.getFileName().toString());
        assertEquals(Path.of("uploads/jaehoon"), photo.getParent());
        assertEquals(3, photo.getNameCount());
    }

    @Test
    @DisplayName("Path 는 파일이 없어도 경로 계산만 한다")
    void pathIsPureWithoutTouchingDisk() {
        Path notReal = Path.of("uploads/없는파일.jpg");

        assertEquals("없는파일.jpg", notReal.getFileName().toString());
    }
}
