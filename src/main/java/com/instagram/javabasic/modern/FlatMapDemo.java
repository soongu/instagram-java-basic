package com.instagram.javabasic.modern;

import java.util.Arrays;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/FlatMapDemo.java
// 중간 연산 다섯 번째 — flatMap. map 은 "하나 → 하나" 였다면, flatMap 은 "하나 → 여러 개" 를 펼쳐요.
// 게시물 한 개의 본문을 단어로 쪼개면 단어 여러 개가 나오죠? 그 여러 개를 한 줄기로 합쳐요.
// 인스타에서 본문 속 해시태그(#로 시작하는 단어)를 전부 모으는 게 딱 이 모양이에요.
public class FlatMapDemo {

    // 모든 게시물의 본문을 단어로 쪼개 → 한 줄기로 펼친 뒤 → # 로 시작하는 해시태그만 남겨요.
    // 같은 해시태그가 여러 번 나올 수 있어요(아직 중복 제거 안 함).
    public static List<String> allTags(List<Post> posts) {
        return posts.stream()
                .flatMap(post -> Arrays.stream(post.getContent().split(" ")))
                .filter(word -> word.startsWith("#"))
                .toList();
    }

    // 위에 distinct 만 더하면 중복 없는 해시태그 목록이 돼요.
    public static List<String> distinctTags(List<Post> posts) {
        return posts.stream()
                .flatMap(post -> Arrays.stream(post.getContent().split(" ")))
                .filter(word -> word.startsWith("#"))
                .distinct()
                .toList();
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> posts = List.of(
                new Post("오늘 카페 #카페 #일상", minji, 250),
                new Post("새 운동화 #패션 #일상", minji, 80));

        System.out.println("모든 해시태그: " + allTags(posts));     // [#카페, #일상, #패션, #일상]
        System.out.println("중복 없는 해시태그: " + distinctTags(posts)); // [#카페, #일상, #패션]
    }
}
