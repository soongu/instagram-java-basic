package com.instagram.javabasic.design.behavioral.solution.day41;

// com/instagram/javabasic/design/behavioral/solution/day41/PopularSortStrategy.java
// "인기순" 정렬 전략이에요. 좋아요 수가 많은 게시물이 맨 앞에 오도록 내림차순으로 정렬해요.

import java.util.Comparator;
import java.util.List;

import com.instagram.javabasic.domain.post.Post;

public class PopularSortStrategy implements FeedSortStrategy {

    @Override
    public List<Post> sort(List<Post> posts) {
        // 원본을 건드리지 않으려고 stream 으로 새 리스트를 만들어요.
        // 좋아요 수가 큰 게시물이 앞에 오도록 reversed() 로 내림차순 정렬해요.
        return posts.stream()
                .sorted(Comparator.comparingInt(Post::getLikeCount).reversed())
                .toList();
    }
}
