package com.instagram.javabasic.nio;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class ChannelCopyDemoTest {

    private final ChannelCopyDemo demo = new ChannelCopyDemo();

    @Test
    @DisplayName("transferTo 로 복사하면 내용과 용량이 원본과 같다")
    void copyViaChannelPreservesContent(@TempDir Path tempDir) throws Exception {
        Path src = tempDir.resolve("video.mp4");
        Files.writeString(src, "큰 동영상 데이터".repeat(50));
        Path dest = tempDir.resolve("video-copy.mp4");

        demo.copyViaChannel(src, dest);

        assertEquals(Files.size(src), Files.size(dest));
        assertEquals(Files.readString(src), Files.readString(dest));
    }

    @Test
    @DisplayName("버퍼로 한 덩어리씩 옮겨도 내용이 그대로다")
    void copyViaBufferPreservesContent(@TempDir Path tempDir) throws Exception {
        Path src = tempDir.resolve("photo.jpg");
        Files.writeString(src, "사진 바이트들".repeat(200));
        Path dest = tempDir.resolve("photo-copy.jpg");

        demo.copyViaBuffer(src, dest);

        assertEquals(Files.readString(src), Files.readString(dest));
    }
}
