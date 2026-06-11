package com.instagram.javabasic.modern.solution.day27;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

// com/instagram/javabasic/modern/solution/day27/StatusReport.java
// Day 27 과제 1 풀이 — groupingBy + counting 으로 "상태별 글 개수" 리포트.
public class StatusReport {

    // 게시물을 상태(PostStatus)별로 묶어 개수를 세요. 결과는 Map<상태, 글 수>.
    public static Map<PostStatus, Long> countByStatus(List<Post> posts) {
        return posts.stream()
                .collect(Collectors.groupingBy(Post::getStatus, Collectors.counting()));
    }
}
