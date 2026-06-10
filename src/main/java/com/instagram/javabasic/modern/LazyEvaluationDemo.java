package com.instagram.javabasic.modern;

import java.util.List;
import java.util.stream.Stream;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/LazyEvaluationDemo.java
// 스트림의 비밀 하나 — 중간 연산은 "쌓아두기만" 해요. 실제로 도는 건 끝에 .toList() 같은
// 최종 연산이 붙는 순간이에요. 이걸 지연 평가(Lazy Evaluation) 라고 불러요.
// peek 은 흐르는 원소를 살짝 들여다보는 중간 연산이에요. 여기에 기록을 남겨 "언제 도는지" 를 눈으로 봐요.
public class LazyEvaluationDemo {

    // 최종 연산(.toList())이 없으면? — 흐름만 만들어두고 아무 일도 안 일어나요.
    // peek 안의 기록이 단 한 줄도 안 쌓여요.
    public static void withoutTerminal(List<Post> posts, List<String> log) {
        Stream<Post> stream = posts.stream()
                .peek(post -> log.add("peek:" + post.getContent()));
        // stream 변수만 만들고 최종 연산을 붙이지 않았어요 → log 는 비어 있어요.
    }

    // 최종 연산(.toList())이 붙으면? — 그제야 원소가 하나씩 흐르며 peek 기록이 쌓여요.
    // 게다가 원소 하나가 끝까지 흐른 뒤 다음 원소가 흘러요(세로로 한 명씩).
    public static List<String> withTerminal(List<Post> posts, List<String> log) {
        return posts.stream()
                .peek(post -> log.add("들어옴:" + post.getContent()))
                .filter(post -> post.getLikeCount() >= 100)
                .peek(post -> log.add("통과:" + post.getContent()))
                .map(Post::getContent)
                .toList();
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> posts = List.of(
                new Post("인기 글", minji, 250),
                new Post("평범한 글", minji, 40));

        java.util.ArrayList<String> log1 = new java.util.ArrayList<>();
        withoutTerminal(posts, log1);
        System.out.println("최종 연산 없을 때 기록 수: " + log1.size());  // 0 — 아무 일도 안 일어남

        java.util.ArrayList<String> log2 = new java.util.ArrayList<>();
        List<String> result = withTerminal(posts, log2);
        System.out.println("최종 연산 있을 때 기록: " + log2);  // [들어옴:인기 글, 통과:인기 글, 들어옴:평범한 글]
        System.out.println("결과: " + result);                 // [인기 글]
    }
}
