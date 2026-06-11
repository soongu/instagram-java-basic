package com.instagram.javabasic.modern;

import java.util.Comparator;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

// com/instagram/javabasic/modern/FindDemo.java
// 흐름에서 "하나" 만 집어오는 최종 연산 — findFirst(맨 앞) · findAny(아무거나).
// 이 둘은 "있을 수도, 없을 수도 있는 상자(Optional)" 를 돌려줘요.
// 지금은 .orElse(기본값) 로 상자를 여는 최소한만 배우고, 제대로 다루는 법은 다음 시간 몫이에요.
public class FindDemo {

    // findFirst — 조건을 만족하는 맨 앞 원소. 없으면 .orElse 로 정한 기본값이 나와요.
    public static String firstPrivateContent(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getStatus() == PostStatus.PRIVATE)
                .map(Post::getContent)
                .findFirst()
                .orElse("(비공개 글 없음)");
    }

    // findAny — 조건을 만족하는 아무거나 하나. 순서가 중요하지 않을 때 더 빨라요.
    public static String anyPopularContent(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getLikeCount() >= 100)
                .map(Post::getContent)
                .findAny()
                .orElse("(인기 글 없음)");
    }

    // max — 가장 좋아요 많은 글. max 도 상자를 돌려줘서 .orElse(null) 로 꺼낸 뒤 null 인지 직접 확인해요.
    // (이 null 확인이 번거롭죠? 다음 시간에 더 안전하게 다루는 법을 배워요.)
    public static String topLikedContent(List<Post> posts) {
        Post top = posts.stream()
                .max(Comparator.comparingInt(Post::getLikeCount))
                .orElse(null);
        return top == null ? "(글 없음)" : top.getContent();
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Post secret = new Post("비밀 일기", minji, 30);
        secret.setStatus(PostStatus.PRIVATE);
        List<Post> posts = List.of(
                new Post("카페 다녀옴", minji, 250),
                secret,
                new Post("산책", minji, 40));

        System.out.println("첫 비공개 글: " + firstPrivateContent(posts)); // 비밀 일기
        System.out.println("인기 글 아무거나: " + anyPopularContent(posts)); // 카페 다녀옴
        System.out.println("최고 인기 글: " + topLikedContent(posts));      // 카페 다녀옴
    }
}
