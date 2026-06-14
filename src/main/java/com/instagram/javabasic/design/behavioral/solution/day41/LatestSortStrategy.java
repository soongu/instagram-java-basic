package com.instagram.javabasic.design.behavioral.solution.day41;

// com/instagram/javabasic/design/behavioral/solution/day41/LatestSortStrategy.java
// "최신순" 정렬 전략이에요. 리스트 뒤쪽에 나중에 올라온 게시물이 있다고 보고,
// 그 순서를 뒤집어 최신 게시물이 맨 앞에 오게 해요.

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.instagram.javabasic.domain.post.Post;

public class LatestSortStrategy implements FeedSortStrategy {

    @Override
    public List<Post> sort(List<Post> posts) {
        // 원본을 그대로 두려고, 먼저 복사본을 만들어요.
        List<Post> result = new ArrayList<>(posts);
        // 복사본만 뒤집어요 — 나중에 들어온 게시물이 맨 앞으로 와요.
        Collections.reverse(result);
        return result;
    }
}
