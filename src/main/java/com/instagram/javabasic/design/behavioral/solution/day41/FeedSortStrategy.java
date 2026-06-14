package com.instagram.javabasic.design.behavioral.solution.day41;

// com/instagram/javabasic/design/behavioral/solution/day41/FeedSortStrategy.java
// 피드를 "어떤 순서로 보여줄지" 라는 정렬 전략을 약속하는 인터페이스예요.
// 최신순이든 인기순이든, 정렬 전략이라면 모두 sort 를 할 줄 알아야 해요.
// 새 정렬 방식이 생겨도 이 약속만 지키면 피드를 다루는 코드는 그대로 둬도 돼요.

import java.util.List;

import com.instagram.javabasic.domain.post.Post;

public interface FeedSortStrategy {

    // 게시물 목록을 받아, 정렬된 새 목록을 돌려줘요.
    // 받은 목록 자체는 건드리지 않는 게 약속이에요(원본 보존).
    List<Post> sort(List<Post> posts);
}
