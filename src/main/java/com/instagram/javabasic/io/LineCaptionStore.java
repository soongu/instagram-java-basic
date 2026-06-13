package com.instagram.javabasic.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// com/instagram/javabasic/io/LineCaptionStore.java
// 여러 줄로 된 글을 "한 줄씩" 편하게 쓰고 읽어요.
//
//   BufferedWriter : 글자를 모아뒀다가 한 번에 쏟아내서 빠르게 써요. newLine() 으로 줄바꿈도 넣어줘요.
//   BufferedReader : 줄 단위로 모아 읽어요. readLine() 한 번에 한 줄씩 가져와요.
//
// 버퍼(buffer)는 "잠깐 모아두는 임시 통" 이에요. 글자 하나하나 파일에 닿는 대신,
// 통에 모았다가 한꺼번에 처리해서 훨씬 빨라요.
public class LineCaptionStore {

    // 여러 줄을 한 줄씩 쓰고, 줄마다 줄바꿈을 넣어줘요.
    public void writeLines(File file, List<String> lines) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }

    // readLine() 으로 한 줄씩 읽어 리스트에 담아 돌려줘요. 더 읽을 게 없으면 null 이 와요.
    public List<String> readLines(File file) throws IOException {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }

    public static void main(String[] args) throws IOException {
        LineCaptionStore store = new LineCaptionStore();
        File file = new File(System.getProperty("java.io.tmpdir"), "lines-demo.txt");

        List<String> captions = List.of("첫 줄 캡션", "둘째 줄 캡션", "셋째 줄 캡션");
        store.writeLines(file, captions);

        List<String> loaded = store.readLines(file);
        System.out.println("읽은 줄 수 → " + loaded.size()); // 3
        for (String line : loaded) {
            System.out.println("  " + line);
        }
    }
}
