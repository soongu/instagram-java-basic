package com.instagram.javabasic.nio.solution.day39;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

// com/instagram/javabasic/nio/solution/day39/MediaCounter.java
// 과제 2 (응용): 업로드 폴더에서 확장자별 파일 개수 세기.
//
// Files.walk 로 하위 폴더까지 재귀로 훑고, filter 로 진짜 파일이면서
// 주어진 확장자로 끝나는 것만 골라 count 해요. walk 가 연 Stream 은 try-with-resources 로 닫아요.
public class MediaCounter {

    // root 아래(하위 폴더 포함)에서 extension 으로 끝나는 파일 개수를 세요.
    public long countByExtension(Path root, String extension) throws IOException {
        try (Stream<Path> paths = Files.walk(root)) {
            return paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(extension))
                    .count();
        }
    }
}
