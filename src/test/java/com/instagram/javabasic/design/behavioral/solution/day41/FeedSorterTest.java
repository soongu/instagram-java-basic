package com.instagram.javabasic.design.behavioral.solution.day41;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.domain.post.Post;

class FeedSorterTest {

    // 좋아요 5, 30, 10 순서로 넣은 게시물 3개를 준비해요.
    private List<Post> samplePosts() {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("첫 번째 글", "jaehoon", 5));
        posts.add(new Post("두 번째 글", "minji", 30));
        posts.add(new Post("세 번째 글", "seungwoo", 10));
        return posts;
    }

    @Test
    @DisplayName("최신순 전략은 나중에 올라온 게시물을 앞에 둔다")
    void latest() {
        List<Post> posts = samplePosts();
        FeedSorter sorter = new FeedSorter(new LatestSortStrategy());

        List<Post> sorted = sorter.sort(posts);

        // 마지막에 넣은 "세 번째 글" 이 맨 앞으로 와야 해요.
        assertEquals("세 번째 글", sorted.get(0).getContent());
        assertEquals("두 번째 글", sorted.get(1).getContent());
        assertEquals("첫 번째 글", sorted.get(2).getContent());
    }

    @Test
    @DisplayName("전략을 인기순으로 갈아끼우면 좋아요 많은 게시물이 앞에 온다")
    void popular() {
        List<Post> posts = samplePosts();
        FeedSorter sorter = new FeedSorter(new LatestSortStrategy());

        // 정렬 도중에 전략을 인기순으로 갈아끼워요.
        sorter.changeStrategy(new PopularSortStrategy());
        List<Post> sorted = sorter.sort(posts);

        // 좋아요가 가장 많은(30) 게시물이 맨 앞, 그다음 10, 5 순서예요.
        assertEquals(30, sorted.get(0).getLikeCount());
        assertEquals(10, sorted.get(1).getLikeCount());
        assertEquals(5, sorted.get(2).getLikeCount());
    }

    @Test
    @DisplayName("정렬해도 원본 리스트는 그대로다")
    void originalUnchanged() {
        List<Post> posts = samplePosts();
        FeedSorter sorter = new FeedSorter(new LatestSortStrategy());

        sorter.sort(posts);

        // 원본은 크기도, 순서도 그대로여야 해요.
        assertEquals(3, posts.size());
        assertEquals("첫 번째 글", posts.get(0).getContent());
        assertEquals("두 번째 글", posts.get(1).getContent());
        assertEquals("세 번째 글", posts.get(2).getContent());
    }

    @Test
    @DisplayName("인기순으로 정렬해도 원본 리스트의 첫 요소는 그대로다")
    void originalUnchangedAfterPopular() {
        List<Post> posts = samplePosts();
        Post firstBefore = posts.get(0);
        FeedSorter sorter = new FeedSorter(new PopularSortStrategy());

        sorter.sort(posts);

        // 원본의 0번 자리에는 여전히 처음 넣었던 그 객체가 있어야 해요.
        assertSame(firstBefore, posts.get(0));
    }
}
