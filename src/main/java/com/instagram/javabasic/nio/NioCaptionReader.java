package com.instagram.javabasic.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

// com/instagram/javabasic/nio/NioCaptionReader.java
// 파일 읽기를 한 줄로. 지난 시간 BufferedReader 를 감싸고 while 로 돌리며 readLine() 하던
// 그 긴 과정을 Files 가 통째로 대신해줘요.
//
//   Files.readString  : 파일 전체를 문자열 하나로. (UTF-8 이 기본값이에요.)
//   Files.readAllLines: 파일을 줄 단위 리스트로. (작은 파일에 편해요.)
//   Files.lines       : 큰 파일을 Stream 으로 한 줄씩 흘려 읽어요. (전부 메모리에 올리지 않아요.)
public class NioCaptionReader {

    // 파일 전체를 문자열 하나로 읽어요. 지난 시간 여러 줄 코드가 이 한 줄이 됐어요.
    public String readWhole(Path file) throws IOException {
        return Files.readString(file);
    }

    // 파일을 줄 단위 리스트로 읽어요.
    public List<String> readLines(Path file) throws IOException {
        return Files.readAllLines(file);
    }

    // 큰 파일은 Stream 으로 한 줄씩. Stream 이 파일을 여니까 try-with-resources 로 꼭 닫아요.
    public long countNonEmptyLines(Path file) throws IOException {
        try (Stream<String> lines = Files.lines(file)) {
            return lines.filter(line -> !line.isBlank()).count();
        }
    }

    public static void main(String[] args) throws IOException {
        NioCaptionReader reader = new NioCaptionReader();
        Path file = Files.createTempFile("caption-", ".txt");
        Files.writeString(file, "오늘 노을 최고\n\n자전거 타고 한강\n");

        System.out.println("전체 내용 →\n" + reader.readWhole(file));
        System.out.println("줄 리스트 → " + reader.readLines(file));
        System.out.println("빈 줄 뺀 줄 수 → " + reader.countNonEmptyLines(file)); // 2
    }
}
