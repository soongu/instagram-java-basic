package com.instagram.javabasic.design.behavioral.solution.day41;

// com/instagram/javabasic/design/behavioral/solution/day41/FeedSorter.java
// 피드 정렬 전략을 품고 있는 클래스예요.
// 정작 정렬하는 일은 자기가 직접 하지 않고, 품고 있는 전략에 맡겨요.
// 그래서 정렬 방식을 통째로 갈아끼워도 이 클래스 자체는 한 줄도 안 바뀌어요.

import java.util.List;

import com.instagram.javabasic.domain.post.Post;

public class FeedSorter {

    // 지금 사용 중인 정렬 전략이에요. 런타임에 다른 전략으로 바꿀 수 있어요.
    private FeedSortStrategy strategy;

    // 처음 만들 때 어떤 전략으로 시작할지 외부에서 받아요.
    public FeedSorter(FeedSortStrategy strategy) {
        this.strategy = strategy;
    }

    // 정렬하던 도중에도 전략을 새것으로 갈아끼워요.
    // 이 메서드 하나로 최신순 → 인기순처럼 정렬 방식을 바꿀 수 있어요.
    public void changeStrategy(FeedSortStrategy strategy) {
        this.strategy = strategy;
    }

    // 실제 정렬은 직접 하지 않고, 지금 품고 있는 전략에게 맡겨요.
    public List<Post> sort(List<Post> posts) {
        return strategy.sort(posts);
    }
}
