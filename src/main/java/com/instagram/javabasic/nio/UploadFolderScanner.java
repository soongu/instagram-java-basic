package com.instagram.javabasic.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

// com/instagram/javabasic/nio/UploadFolderScanner.java
// 업로드 폴더를 통째로 훑어요. 지난 시간 java.io.File 로 디렉토리를 순회하던 일이 Stream 한 줄이 돼요.
//
//   Files.list : 폴더 "바로 아래" 한 단계만 봐요. (하위 폴더 속은 안 들어가요)
//   Files.walk : 하위 폴더 속까지 "재귀로" 전부 훑어요.
//
// list / walk 는 파일을 여는 Stream 을 돌려줘요. 그래서 try-with-resources 로 꼭 닫아야 해요.
public class UploadFolderScanner {

    // 폴더 바로 아래 항목만 이름순으로 모아요.
    public List<Path> listDirectly(Path dir) throws IOException {
        try (Stream<Path> entries = Files.list(dir)) {
            return entries.sorted().toList();
        }
    }

    // 하위 폴더까지 재귀로 훑어 .jpg 파일 개수를 세요.
    public long countJpgRecursively(Path root) throws IOException {
        try (Stream<Path> paths = Files.walk(root)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".jpg"))
                    .count();
        }
    }

    public static void main(String[] args) throws IOException {
        UploadFolderScanner scanner = new UploadFolderScanner();
        Path root = Files.createTempDirectory("uploads-");
        Files.createDirectories(root.resolve("jaehoon"));
        Files.createDirectories(root.resolve("minji"));
        Files.writeString(root.resolve("jaehoon/a.jpg"), "x");
        Files.writeString(root.resolve("jaehoon/b.jpg"), "x");
        Files.writeString(root.resolve("minji/c.jpg"), "x");
        Files.writeString(root.resolve("minji/memo.txt"), "x");

        System.out.println("바로 아래 항목 → " + scanner.listDirectly(root));
        System.out.println("전체 .jpg 개수 → " + scanner.countJpgRecursively(root)); // 3
    }
}
