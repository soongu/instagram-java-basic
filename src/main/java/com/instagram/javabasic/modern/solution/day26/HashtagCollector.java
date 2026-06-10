package com.instagram.javabasic.modern.solution.day26;

import java.util.Arrays;
import java.util.List;

import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/solution/day26/HashtagCollector.java
// Day 26 과제 3 풀이 — flatMap 으로 게시물별 해시태그를 한 줄기로 펼치기 (중복 포함).
public class HashtagCollector {

    // 모든 게시물 본문에서 # 로 시작하는 단어만 한 리스트로. 중복은 일부러 그대로 둬요.
    public static List<String> allTags(List<Post> posts) {
        return posts.stream()
                .flatMap(post -> Arrays.stream(post.getContent().split(" ")))
                .filter(word -> word.startsWith("#"))
                .toList();
    }
}
