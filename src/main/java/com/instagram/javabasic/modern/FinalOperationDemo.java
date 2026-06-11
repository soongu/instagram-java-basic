package com.instagram.javabasic.modern;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

// com/instagram/javabasic/modern/FinalOperationDemo.java
// 최종 연산 첫걸음 — 지난 시간 중간 연산(filter·map)은 최종 연산이 붙기 전엔 아무 일도 안 했어요.
// collect·count·forEach 같은 "최종 연산" 이 흐름의 방아쇠를 당겨야 비로소 결과가 나와요.
public class FinalOperationDemo {

    // collect(toList()) — 흐름을 진짜 List 로 모아요. 이 한 줄이 방아쇠예요.
    public static List<String> publicContents(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getStatus() == PostStatus.PUBLIC)
                .map(Post::getContent)
                .collect(Collectors.toList());
    }

    // count — 흐름에 원소가 몇 개 남았는지 세요. 결과는 long.
    public static long countPopular(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getLikeCount() >= 100)
                .count();
    }

    // forEach — 각 원소마다 작업을 실행해요(출력·기록 같은 "부작용"). 여기선 한 줄씩 모아 담아요.
    public static List<String> feedLines(List<Post> posts) {
        List<String> lines = new ArrayList<>();
        posts.stream()
                .filter(post -> post.getStatus() == PostStatus.PUBLIC)
                .forEach(post -> lines.add(post.getAuthorName() + ": " + post.getContent()));
        return lines;
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Post secret = new Post("비밀 글", minji, 300);
        secret.setStatus(PostStatus.PRIVATE);
        List<Post> posts = List.of(
                new Post("카페 다녀옴", minji, 250),
                secret,
                new Post("평범한 하루", minji, 40));

        System.out.println("공개 글 내용: " + publicContents(posts)); // [카페 다녀옴, 평범한 하루]
        System.out.println("인기 글 개수: " + countPopular(posts));    // 2 (250, 300)
        feedLines(posts).forEach(System.out::println);                // minji: 카페..., minji: 평범...
    }
}
