package com.instagram.javabasic.modern;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

// com/instagram/javabasic/modern/CollectorsDemo.java
// collect 의 진짜 힘은 Collectors 와 함께 나와요. 흐름을 Map·Set·문자열 등 원하는 모양으로 모아요.
// groupingBy(분류) · toSet/toMap/joining(기본) · partitioningBy(둘로 양분) 을 익혀요.
public class CollectorsDemo {

    // groupingBy — 작성자 이름을 기준으로 글을 묶어요. 결과는 Map<작성자, 그 사람 글 목록>.
    public static Map<String, List<Post>> postsByAuthor(List<Post> posts) {
        return posts.stream()
                .collect(Collectors.groupingBy(Post::getAuthorName));
    }

    // groupingBy + counting — 같은 해시태그가 몇 번 나왔는지 세요(인기 해시태그 순위의 재료).
    public static Map<String, Long> hashtagFrequency(List<Post> posts) {
        return posts.stream()
                .flatMap(post -> Arrays.stream(post.getContent().split(" ")))
                .filter(word -> word.startsWith("#"))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    // toSet — List 와 달리 중복을 자동으로 걸러요. 고유한 작성자 집합.
    public static Set<String> distinctAuthors(List<Post> posts) {
        return posts.stream()
                .map(Post::getAuthorName)
                .collect(Collectors.toSet());
    }

    // toMap — 원소를 직접 key-value 쌍으로 재구성해요. 이름 -> 팔로워 수.
    public static Map<String, Integer> followerMap(List<Member> members) {
        return members.stream()
                .collect(Collectors.toMap(Member::getUsername, Member::getFollowers));
    }

    // joining — 흐름의 문자열을 구분자로 이어 한 문장으로. 모든 닉네임을 쉼표로 연결.
    public static String usernamesJoined(List<Member> members) {
        return members.stream()
                .map(Member::getUsername)
                .collect(Collectors.joining(", "));
    }

    // partitioningBy — 조건(true/false) 하나로 흐름을 둘로 나눠요. 공개 글 vs 나머지.
    public static Map<Boolean, List<Post>> partitionByPublic(List<Post> posts) {
        return posts.stream()
                .collect(Collectors.partitioningBy(post -> post.getStatus() == PostStatus.PUBLIC));
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        Post secret = new Post("비밀 #일상", minji, 90);
        secret.setStatus(PostStatus.PRIVATE);
        List<Post> posts = List.of(
                new Post("카페 #카페 #일상", minji, 250),
                new Post("코딩 #개발 #일상", jaehoon, 120),
                secret);

        System.out.println("작성자별 글 수: " + postsByAuthor(posts).get("minji").size()); // 2
        System.out.println("해시태그 빈도: " + hashtagFrequency(posts));   // {#일상=3, #카페=1, #개발=1}
        System.out.println("고유 작성자: " + distinctAuthors(posts));      // [minji, jaehoon]
        System.out.println("팔로워 맵: " + followerMap(List.of(minji, jaehoon)));
        System.out.println("닉네임 연결: " + usernamesJoined(List.of(minji, jaehoon))); // minji, jaehoon
        System.out.println("공개 글 수: " + partitionByPublic(posts).get(true).size()); // 2
    }
}
