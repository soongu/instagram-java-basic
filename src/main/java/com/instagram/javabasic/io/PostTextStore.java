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

// com/instagram/javabasic/io/PostTextStore.java
// 게시물 목록을 "사람이 읽을 수 있는 한 줄 텍스트" 로 저장하고 다시 불러와요.
//
// 한 게시물을 "id|작성자|캡션" 형태 한 줄로 만들어요. (직접 손으로 만든 직렬화예요.)
// 불러올 때는 줄을 막대(|) 기준으로 잘라서 다시 Post 로 조립해요.
//
// 단순화를 위해 캡션 안에는 막대(|) 가 없다고 가정해요 — 이게 손수 만든 포맷의 한계예요.
// (그래서 보통은 JSON 같은 검증된 포맷을 써요. 지금은 원리를 보려고 직접 만들어봐요.)
public class PostTextStore {

    private static final String DELIMITER = "|";

    // 게시물 목록을 한 줄씩 텍스트로 저장해요.
    public void saveAll(File file, List<Post> posts) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(file, StandardCharsets.UTF_8))) {
            for (Post post : posts) {
                writer.write(post.id() + DELIMITER + post.author() + DELIMITER + post.caption());
                writer.newLine();
            }
        }
    }

    // 한 줄씩 읽어 막대(|) 기준으로 잘라 Post 로 복원해요.
    public List<Post> loadAll(File file) throws IOException {
        List<Post> posts = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(file, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // limit 3 으로 잘라 앞 두 칸만 구분하고 캡션은 통째로 남겨요.
                String[] parts = line.split("\\" + DELIMITER, 3);
                long id = Long.parseLong(parts[0]);
                String author = parts[1];
                String caption = parts[2];
                posts.add(new Post(id, author, caption));
            }
        }
        return posts;
    }

    public static void main(String[] args) throws IOException {
        PostTextStore store = new PostTextStore();
        File file = new File(System.getProperty("java.io.tmpdir"), "posts-text-demo.txt");

        List<Post> posts = List.of(
                new Post(1L, "jaehoon", "첫 게시물이에요"),
                new Post(2L, "minji", "오늘 날씨 좋네요"));
        store.saveAll(file, posts);

        List<Post> loaded = store.loadAll(file);
        System.out.println("불러온 게시물 수 → " + loaded.size()); // 2
        for (Post post : loaded) {
            System.out.println("  " + post.id() + " / " + post.author() + " / " + post.caption());
        }
    }
}
