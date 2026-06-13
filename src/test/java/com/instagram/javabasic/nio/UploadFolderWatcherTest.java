package com.instagram.javabasic.nio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class UploadFolderWatcherTest {

    private final UploadFolderWatcher watcher = new UploadFolderWatcher();

    @Test
    @DisplayName("폴더에 새 파일이 생기면 그 이름을 감지한다")
    void detectsNewlyCreatedFile(@TempDir Path tempDir) {
        // 전체를 넉넉한 한도로 감싸 어떤 환경에서도 무한정 멈추지 않게 해요.
        assertTimeoutPreemptively(Duration.ofSeconds(30), () -> {
            // 감시가 시작된 뒤 파일을 떨어뜨리도록 별도 스레드를 데몬으로 띄워요.
            Thread uploader = new Thread(() -> {
                try {
                    Thread.sleep(500);
                    Files.writeString(tempDir.resolve("new-post.jpg"), "사진");
                } catch (Exception ignored) {
                }
            });
            uploader.setDaemon(true);
            uploader.start();

            Optional<String> detected = watcher.waitForNextFile(tempDir, Duration.ofSeconds(20));

            assertTrue(detected.isPresent(), "새 파일이 감지돼야 한다");
            assertEquals("new-post.jpg", detected.get());
        });
    }

    @Test
    @DisplayName("변화가 없으면 시간이 지나 비어 있음을 돌려준다")
    void returnsEmptyWhenNothingHappens(@TempDir Path tempDir) {
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            Optional<String> detected = watcher.waitForNextFile(tempDir, Duration.ofMillis(300));

            assertTrue(detected.isEmpty(), "변화가 없으면 비어 있어야 한다");
        });
    }
}
