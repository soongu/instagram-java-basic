package com.instagram.javabasic.io;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

// com/instagram/javabasic/io/TextCaptionStore.java
// 게시물 캡션 같은 "글자(문자)" 를 파일에 쓰고 읽어요.
//
//   FileWriter : 글자를 파일에 쓰는 펜이에요.
//   FileReader : 파일에서 글자를 읽어오는 눈이에요.
//
// 바이트와 다른 점은, 글자는 "어떤 약속(인코딩)으로 바이트로 바꿀지" 를 정해줘야 해요.
// 한글이 깨지지 않으려면 항상 UTF-8 을 쓰는 게 안전해요. 그래서 StandardCharsets.UTF_8 을 직접 넘겨요.
public class TextCaptionStore {

    // 문자열(캡션)을 UTF-8 로 파일에 써요.
    public void writeText(File file, String caption) throws IOException {
        try (FileWriter writer = new FileWriter(file, StandardCharsets.UTF_8)) {
            writer.write(caption);
        }
    }

    // 파일에 든 글자를 UTF-8 로 한 글자씩 끝까지 읽어 하나의 문자열로 돌려줘요.
    public String readText(File file) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (FileReader reader = new FileReader(file, StandardCharsets.UTF_8)) {
            int c;
            while ((c = reader.read()) != -1) {
                sb.append((char) c);
            }
        }
        return sb.toString();
    }

    // 같은 글자라도 "글자 수" 와 "바이트 수" 는 다를 수 있어요.
    // UTF-8 에서 한글 한 글자는 보통 3바이트라서, 한글이 섞이면 바이트 수가 더 커져요.
    public int byteLength(String caption) {
        return caption.getBytes(StandardCharsets.UTF_8).length;
    }

    public static void main(String[] args) throws IOException {
        TextCaptionStore store = new TextCaptionStore();
        File file = new File(System.getProperty("java.io.tmpdir"), "caption-demo.txt");

        String caption = "안녕하세요 🌙 첫 게시물";
        store.writeText(file, caption);

        String loaded = store.readText(file);
        System.out.println("읽은 캡션      → " + loaded);                 // 안녕하세요 🌙 첫 게시물
        System.out.println("글자 길이      → " + loaded.length());        // char 단위 길이
        System.out.println("UTF-8 바이트 수 → " + store.byteLength(loaded)); // 더 큼 (한글 1자=3바이트)
    }
}
