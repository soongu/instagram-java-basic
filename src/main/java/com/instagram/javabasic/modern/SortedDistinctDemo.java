package com.instagram.javabasic.modern;

import java.util.Comparator;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// com/instagram/javabasic/modern/SortedDistinctDemo.java
// 중간 연산 세 번째·네 번째 — sorted(정렬) 와 distinct(중복 제거).
// sorted 는 Day 18 에서 배운 Comparator 를 그대로 받아요 — 무엇을 기준으로 줄 세울지 정해요.
// distinct 는 equals 가 같은 원소를 한 번씩만 남겨요.
public class SortedDistinctDemo {

    // 좋아요 많은 순(내림차순)으로 줄 세우기. comparingInt(...).reversed() 가 "큰 게 앞으로" 예요.
    public static List<Post> byLikesDesc(List<Post> posts) {
        return posts.stream()
                .sorted(Comparator.comparingInt(Post::getLikeCount).reversed())
                .toList();
    }

    // 작성자 이름만 뽑되 중복은 한 번씩만 — 같은 사람이 여러 글을 써도 이름은 한 번.
    public static List<String> distinctAuthors(List<Post> posts) {
        return posts.stream()
                .map(Post::getAuthorName)
                .distinct()
                .toList();
    }

    // 중복을 없앤 뒤 가나다순 정렬까지 — 중간 연산을 이어 붙여요.
    public static List<String> sortedDistinctAuthors(List<Post> posts) {
        return posts.stream()
                .map(Post::getAuthorName)
                .distinct()
                .sorted()
                .toList();
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        List<Post> posts = List.of(
                new Post("글 A", minji, 80),
                new Post("글 B", jaehoon, 250),
                new Post("글 C", minji, 120));

        System.out.println("좋아요 내림차순: " + byLikesDesc(posts).get(0).getLikeCount()); // 250
        System.out.println("중복 없는 작성자: " + distinctAuthors(posts));                  // [minji, jaehoon]
        System.out.println("정렬된 작성자: " + sortedDistinctAuthors(posts));               // [jaehoon, minji]
    }
}
