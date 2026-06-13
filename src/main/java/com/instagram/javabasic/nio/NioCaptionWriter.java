package com.instagram.javabasic.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

// com/instagram/javabasic/nio/NioCaptionWriter.java
// 파일 쓰기도 한 줄로. 지난 시간 BufferedWriter + newLine() 묶음이 Files 한 줄로 줄어요.
//
//   Files.writeString : 문자열 하나를 통째로 써요. 파일이 없으면 만들고, 있으면 덮어써요.
//   Files.write       : 줄 리스트(List<String>) 를 한 번에 줄바꿈까지 넣어 써요.
//
// StandardOpenOption 은 "어떻게 열까" 를 고르는 옵션이에요.
//   CREATE : 없으면 새로 만들기   APPEND : 끝에 덧붙이기   TRUNCATE_EXISTING : 기존 내용 비우고 쓰기
public class NioCaptionWriter {

    // 파일 전체를 한 번에 써요. (없으면 생성, 있으면 통째로 덮어쓰기)
    public void writeWhole(Path file, String content) throws IOException {
        Files.writeString(file, content);
    }

    // 여러 줄을 한 번에 써요. 줄바꿈은 Files 가 알아서 넣어줘요.
    public void writeLines(Path file, List<String> lines) throws IOException {
        Files.write(file, lines);
    }

    // 기존 파일 끝에 한 줄 덧붙여요. (댓글이 새로 달릴 때처럼)
    public void appendLine(Path file, String line) throws IOException {
        Files.writeString(file, line + System.lineSeparator(),
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
    }

    public static void main(String[] args) throws IOException {
        NioCaptionWriter writer = new NioCaptionWriter();
        Path file = Files.createTempFile("comments-", ".txt");

        writer.writeLines(file, List.of("좋아요!", "사진 멋져요"));
        writer.appendLine(file, "팔로우했어요");

        System.out.println("저장된 내용 →\n" + Files.readString(file));
    }
}
