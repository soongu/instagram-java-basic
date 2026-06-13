package com.instagram.javabasic.nio;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.time.Duration;
import java.util.Optional;

// com/instagram/javabasic/nio/UploadFolderWatcher.java
// WatchService 는 "폴더를 지켜보다가 파일이 새로 생기면 알려주는" 감시자예요.
//
// 지금까지는 우리가 먼저 "읽어줘" 하고 물어봤어요(폴링). WatchService 는 반대로,
// 변화가 생기면 운영체제가 "여기 새 파일 생겼어요!" 하고 알려줘요(이벤트).
//
// take() 는 이벤트가 올 때까지 막혀요(블로킹). 그래서 보통 감시는 별도 스레드에서 돌려요.
// 여기서는 입문용으로 "정해진 시간만 기다리는" poll(timeout) 을 써서 무한정 멈추지 않게 해요.
public class UploadFolderWatcher {

    // dir 에 새 파일이 생기길 timeout 동안 기다려요. 생기면 그 파일 이름을, 시간 안에 없으면 비어 있음을 돌려줘요.
    public Optional<String> waitForNextFile(Path dir, Duration timeout) throws IOException, InterruptedException {
        try (WatchService watchService = FileSystems.getDefault().newWatchService()) {
            // 이 폴더에서 "새 파일 생성(ENTRY_CREATE)" 이벤트를 지켜봐 달라고 등록해요.
            dir.register(watchService, StandardWatchEventKinds.ENTRY_CREATE);

            // timeout 만큼만 기다려요. 이벤트가 오면 key, 안 오면 null.
            WatchKey key = watchService.poll(timeout.toMillis(), java.util.concurrent.TimeUnit.MILLISECONDS);
            if (key == null) {
                return Optional.empty();
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                Path created = (Path) event.context();   // 새로 생긴 파일 이름
                return Optional.of(created.toString());
            }
            return Optional.empty();
        }
    }

    public static void main(String[] args) throws Exception {
        UploadFolderWatcher watcher = new UploadFolderWatcher();
        Path dir = Files.createTempDirectory("watch-");

        // 0.2초 뒤에 새 파일을 떨어뜨리는 별도 스레드 (다른 사용자가 업로드한 셈)
        Thread uploader = new Thread(() -> {
            try {
                Thread.sleep(200);
                Files.writeString(dir.resolve("new-post.jpg"), "사진");
            } catch (Exception ignored) {
            }
        });
        uploader.start();

        Optional<String> detected = watcher.waitForNextFile(dir, Duration.ofSeconds(15));
        System.out.println("감지된 새 파일 → " + detected.orElse("(시간 내 없음)"));
    }
}
