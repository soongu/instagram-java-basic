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

// 과제 2 (응용) 예시 답안 — 좋아요 수까지 한 줄 텍스트로 저장하고 복원해요.
// 한 게시물을 "id|author|caption|likes" 형태 한 줄로 만들어요.
public class LikedPostStore {

    private static final String DELIMITER = "|";

    // 게시물 목록을 한 줄씩 텍스트로 저장해요.
    public void saveAll(File file, List<LikedPost> posts) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8))) {
            for (LikedPost post : posts) {
                writer.write(post.id() + DELIMITER + post.author() + DELIMITER
                        + post.caption() + DELIMITER + post.likes());
                writer.newLine();
            }
        }
    }

    // 한 줄씩 읽어 막대(|) 기준으로 잘라 LikedPost 로 복원해요. 숫자 칸은 형 변환해요.
    public List<LikedPost> loadAll(File file) throws IOException {
        List<LikedPost> posts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\" + DELIMITER, 4);
                long id = Long.parseLong(parts[0]);
                String author = parts[1];
                String caption = parts[2];
                int likes = Integer.parseInt(parts[3]);
                posts.add(new LikedPost(id, author, caption, likes));
            }
        }
        return posts;
    }
}
