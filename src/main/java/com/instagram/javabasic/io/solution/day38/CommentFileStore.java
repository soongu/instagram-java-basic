package com.instagram.javabasic.io.solution.day38;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

// 과제 1 (기초) 예시 답안 — 댓글을 파일에 한 줄씩 저장하고 다시 불러와요.
// BufferedWriter/BufferedReader 로 버퍼링하고, 인코딩은 UTF-8 로 명시해 한글이 깨지지 않게 해요.
public class CommentFileStore {

    // 댓글 목록을 한 줄에 하나씩 저장해요.
    public void saveComments(File file, List<String> comments) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8))) {
            for (String comment : comments) {
                writer.write(comment);
                writer.newLine();
            }
        }
    }

    // readLine() 으로 한 줄씩 읽어 리스트에 담아 돌려줘요. null 이 오면 끝이에요.
    public List<String> loadComments(File file) throws IOException {
        List<String> comments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                comments.add(line);
            }
        }
        return comments;
    }
}
