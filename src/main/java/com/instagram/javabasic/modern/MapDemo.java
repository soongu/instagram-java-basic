package com.instagram.javabasic.modern;

import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/MapDemo.java
// 중간 연산 두 번째 — map. "거르기(filter)" 가 줄이는 거라면, "바꾸기(map)" 는 모양을 바꾸는 거예요.
// 게시물(Post) 흐름을 → 제목(String) 흐름으로, 회원(Member) 흐름을 → 이름(String) 흐름으로.
// 흐름에 흐르는 원소의 타입 자체가 바뀌어요 (Post → String, Post → Integer).
public class MapDemo {

    // 게시물 흐름 → 내용(String) 흐름. Post::getContent 는 "각 게시물에서 내용만 꺼내라" 예요.
    public static List<String> contents(List<Post> posts) {
        return posts.stream()
                .map(Post::getContent)
                .toList();
    }

    // 회원 흐름 → 이름(String) 흐름
    public static List<String> usernames(List<Member> members) {
        return members.stream()
                .map(Member::getUsername)
                .toList();
    }

    // 게시물 흐름 → 좋아요 수(Integer) 흐름. Post 가 숫자로 바뀌었어요.
    public static List<Integer> likeCounts(List<Post> posts) {
        return posts.stream()
                .map(Post::getLikeCount)
                .toList();
    }

    // filter 로 거른 뒤 map 으로 바꾸기 — 인기 글의 "내용만" 뽑아요.
    public static List<String> popularContents(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getLikeCount() >= 100)
                .map(Post::getContent)
                .toList();
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> posts = List.of(
                new Post("카페 다녀옴", minji, 250),
                new Post("운동 시작", minji, 40));

        System.out.println("내용 목록: " + contents(posts));        // [카페 다녀옴, 운동 시작]
        System.out.println("좋아요 목록: " + likeCounts(posts));     // [250, 40]
        System.out.println("인기 글 내용: " + popularContents(posts)); // [카페 다녀옴]
    }
}
