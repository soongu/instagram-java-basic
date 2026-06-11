package com.instagram.javabasic.modern;

import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/ReduceDemo.java
// reduce — 흐름의 원소들을 "하나" 로 짜내요. 초기값을 주면 결과 타입이 깔끔하게 정해져요.
// reduce(초기값, (누적값, 다음원소) -> 새 누적값) 형태예요.
public class ReduceDemo {

    // 모든 글의 좋아요를 합산 — 초기값 0 에서 시작해 하나씩 더해요.
    public static int totalLikes(List<Post> posts) {
        return posts.stream()
                .map(Post::getLikeCount)
                .reduce(0, Integer::sum);
    }

    // 모든 회원의 팔로워 합계 — 같은 reduce 패턴이에요.
    public static int totalFollowers(List<Member> members) {
        return members.stream()
                .map(Member::getFollowers)
                .reduce(0, Integer::sum);
    }

    // reduce 는 합산만 하는 게 아니에요 — 누적 자리에 "더 큰 값" 을 남기면 최댓값이 돼요.
    // 초기값 0 이 있어서 결과는 늘 int 하나로 나와요(빈 흐름이면 0).
    public static int mostLikes(List<Post> posts) {
        return posts.stream()
                .map(Post::getLikeCount)
                .reduce(0, Integer::max);
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        List<Post> posts = List.of(
                new Post("카페", minji, 250),
                new Post("코딩", jaehoon, 120),
                new Post("산책", minji, 40));

        System.out.println("좋아요 합계: " + totalLikes(posts));              // 410
        System.out.println("팔로워 합계: " + totalFollowers(List.of(minji, jaehoon))); // 9740
        System.out.println("최다 좋아요: " + mostLikes(posts));              // 250
    }
}
