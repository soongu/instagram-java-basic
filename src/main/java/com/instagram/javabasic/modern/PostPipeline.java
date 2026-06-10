package com.instagram.javabasic.modern;

import java.util.Comparator;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

// com/instagram/javabasic/modern/PostPipeline.java
// 오늘 배운 중간 연산들을 하나로 이어 붙이면 "데이터 파이프라인" 이 돼요.
// 거르고(filter) → 줄 세우고(sorted) → 모양 바꾸기(map) 를 한 흐름에 죽 이어요.
// "공개된 인기 글을, 좋아요 많은 순으로, 제목만" — 한 문장이 그대로 코드가 돼요.
public class PostPipeline {

    // 공개 + 인기(100+) → 좋아요 내림차순 → 내용만. 세 단계가 한 줄기로 흘러요.
    public static List<String> popularPublicTitles(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getStatus() == PostStatus.PUBLIC)
                .filter(post -> post.getLikeCount() >= 100)
                .sorted(Comparator.comparingInt(Post::getLikeCount).reversed())
                .map(Post::getContent)
                .toList();
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Post secret = new Post("비밀 인기 글", minji, 500);
        secret.setStatus(PostStatus.PRIVATE);
        List<Post> posts = List.of(
                new Post("두 번째 인기 글", minji, 120),
                new Post("가장 인기 글", minji, 300),
                secret,
                new Post("평범한 글", minji, 40));

        System.out.println("공개 인기 글 제목(좋아요 순): " + popularPublicTitles(posts));
        // [가장 인기 글, 두 번째 인기 글] — 비밀(500)·평범(40)은 빠지고, 큰 순서로 줄 섬
    }
}
