package com.instagram.javabasic.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

// com/instagram/javabasic/nio/MediaFileManager.java
// 파일과 폴더를 직접 다뤄요. 업로드된 사진을 옮기고·복사하고·지우고·용량을 재는 일이에요.
//
//   Files.exists / createDirectories : 있는지 확인하고, 폴더를 (중간 폴더까지) 만들어요.
//   Files.copy / move                : 복사하고, 옮겨요. REPLACE_EXISTING 으로 덮어쓰기 허용.
//   Files.deleteIfExists / size      : 있으면 지우고, 바이트 단위 용량을 재요.
public class MediaFileManager {

    public boolean exists(Path path) {
        return Files.exists(path);
    }

    // 중간 폴더가 없어도 한 번에 다 만들어줘요. (uploads/jaehoon/2026 처럼 깊어도 OK)
    public void ensureDir(Path dir) throws IOException {
        Files.createDirectories(dir);
    }

    // 썸네일을 만들 때처럼 원본은 두고 복사해요.
    public void copyFile(Path src, Path dest) throws IOException {
        Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
    }

    // 임시 폴더에 올라온 사진을 영구 폴더로 옮겨요. (원본은 사라져요)
    public void moveFile(Path src, Path dest) throws IOException {
        Files.move(src, dest, StandardCopyOption.REPLACE_EXISTING);
    }

    // 있으면 지우고 true, 원래 없었으면 false. (없어도 예외가 안 나요)
    public boolean removeIfExists(Path path) throws IOException {
        return Files.deleteIfExists(path);
    }

    // 파일 용량을 바이트로 재요. (업로드 용량 제한 검사에 써요)
    public long fileSize(Path path) throws IOException {
        return Files.size(path);
    }

    public static void main(String[] args) throws IOException {
        MediaFileManager manager = new MediaFileManager();
        Path temp = Files.createTempDirectory("media-");
        Path uploads = temp.resolve("uploads/jaehoon");

        manager.ensureDir(uploads);
        Path original = uploads.resolve("post1.jpg");
        Files.writeString(original, "가짜 이미지 데이터");

        Path thumbnail = uploads.resolve("post1-thumb.jpg");
        manager.copyFile(original, thumbnail);

        System.out.println("썸네일 생겼나? → " + manager.exists(thumbnail));   // true
        System.out.println("원본 용량(byte) → " + manager.fileSize(original));
    }
}
