package com.instagram.javabasic.modern;

import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/ParallelStreamDemo.java
// 병렬 스트림 — .parallel() 한 줄이면 여러 갈래(스레드)로 동시에 흘러요.
// 합산처럼 순서가 상관없는 일에는 도움이 되지만, 작은 데이터엔 오히려 느리고
// 공유 변수를 건드리면 결과가 들쭉날쭉해질 수 있어요. "기본은 순차, 병렬은 신중하게" 예요.
public class ParallelStreamDemo {

    // 순차 합산 — 한 갈래로 차례차례 더해요. Day 27 에서 배운 reduce 를 그대로 써요.
    public static int sequentialTotalLikes(List<Post> posts) {
        return posts.stream()
                .map(Post::getLikeCount)
                .reduce(0, Integer::sum);
    }

    // 병렬 합산 — .parallelStream() 으로 여러 갈래로 나눠 더해요. 합산은 순서가 상관없어 결과가 같아요.
    public static int parallelTotalLikes(List<Post> posts) {
        return posts.parallelStream()
                .map(Post::getLikeCount)
                .reduce(0, Integer::sum);
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> posts = List.of(
                new Post("a", minji, 10),
                new Post("b", minji, 20),
                new Post("c", minji, 30));

        System.out.println("순차 합계: " + sequentialTotalLikes(posts)); // 60
        System.out.println("병렬 합계: " + parallelTotalLikes(posts));   // 60 (결과는 같음)

        // forEach 는 병렬에서 순서를 보장하지 않아요 — 출력이 뒤섞일 수 있어요.
        System.out.print("병렬 순서(뒤섞일 수 있음): ");
        posts.parallelStream().forEach(post -> System.out.print(post.getContent() + " "));
        System.out.println();
    }
}
