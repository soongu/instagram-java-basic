package com.instagram.javabasic.modern;

import java.util.List;
import java.util.function.Predicate;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

// com/instagram/javabasic/modern/FilterDemo.java
// 중간 연산 첫 번째 — filter. Day 25 에서 만든 PostFilter 와 똑같이 "조건을 람다로" 넘겨요.
// 다른 점은 우리가 for 를 안 쓴다는 것뿐이에요. .filter(조건) 한 줄이면 끝나요.
// filter 를 여러 번 이어 붙이면 "그리고(AND)" 처럼 조건이 겹쳐요.
public class FilterDemo {

    // Day 25 의 Predicate 를 그대로 재사용 — 좋아요 100 이상이면 인기 글
    public static final Predicate<Post> POPULAR = post -> post.getLikeCount() >= 100;

    // 인기 글만 거르기 — 조건 하나
    public static List<Post> popularPosts(List<Post> posts) {
        return posts.stream()
                .filter(POPULAR)
                .toList();
    }

    // 공개 + 인기 글만 — filter 를 두 번 이어 붙이면 두 조건을 모두 만족하는 것만 남아요.
    public static List<Post> publicAndPopular(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getStatus() == PostStatus.PUBLIC)
                .filter(POPULAR)
                .toList();
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Post secret = new Post("비밀 인기 글", minji, 300);
        secret.setStatus(PostStatus.PRIVATE);
        List<Post> posts = List.of(
                new Post("인기 공개 글", minji, 250),
                secret,
                new Post("평범한 글", minji, 40));

        System.out.println("인기 글: " + popularPosts(posts).size());         // 2 (공개 250 + 비밀 300)
        System.out.println("공개 + 인기 글: " + publicAndPopular(posts).size()); // 1 (공개 250만)
    }
}
