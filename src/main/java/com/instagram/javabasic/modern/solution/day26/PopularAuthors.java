package com.instagram.javabasic.modern.solution.day26;

import java.util.Comparator;
import java.util.List;

import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/solution/day26/PopularAuthors.java
// Day 26 과제 1·2 풀이 — filter + map (그리고 distinct + sorted) 조합.
public class PopularAuthors {

    // 과제 1 — 좋아요 200 이상 글의 작성자 이름 (중복 포함)
    public static List<String> names(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getLikeCount() >= 200)
                .map(Post::getAuthorName)
                .toList();
    }

    // 과제 2 — 위 결과를 중복 없이 가나다순으로
    public static List<String> distinctSortedNames(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getLikeCount() >= 200)
                .map(Post::getAuthorName)
                .distinct()
                .sorted()
                .toList();
    }
}
