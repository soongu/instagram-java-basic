package com.instagram.javabasic.modern;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/StreamCollectComprehensive.java
// 지난 시간 중간 연산(filter·map·sorted·limit)과 오늘 최종 연산(groupingBy·counting·joining)을
// 한 줄기로 엮어요. "좋아요 200 이상 글을 작성자별로 세서, 많이 쓴 상위 N명을 쉼표로 연결" 해봐요.
public class StreamCollectComprehensive {

    public static String topAuthorsByPopularPosts(List<Post> posts, int limit) {
        // 1단계: 좋아요 200+ 글만 작성자별로 묶어 개수를 세요 → Map<작성자, 글 수>
        Map<String, Long> countByAuthor = posts.stream()
                .filter(post -> post.getLikeCount() >= 200)
                .collect(Collectors.groupingBy(Post::getAuthorName, Collectors.counting()));

        // 2단계: 그 Map 을 다시 흐름으로 펼쳐 글 수 많은 순으로 정렬 → 상위 N명 이름만 이어 붙여요
        return countByAuthor.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .limit(limit)
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        Member seungwoo = new Member("seungwoo", 320, 12, 2, 60);
        List<Post> posts = List.of(
                new Post("카페", minji, 250),
                new Post("여행", minji, 300),
                new Post("코딩", jaehoon, 210),
                new Post("산책", seungwoo, 50));

        // minji 2개, jaehoon 1개 (seungwoo 는 200 미만이라 빠짐)
        System.out.println("인기 작성자 상위 2명: " + topAuthorsByPopularPosts(posts, 2)); // minji, jaehoon
    }
}
