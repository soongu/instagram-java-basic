package com.instagram.javabasic.modern;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

// com/instagram/javabasic/modern/StreamComprehensive.java
// 오늘 배운 중간 연산(filter·map·sorted·distinct·flatMap)과 limit 을 자유롭게 조합해
// 인스타그램의 여러 요구사항을 한 줄기씩 풀어봐요.
// 결과를 "모으는" 본격적인 도구(개수 세기·그룹 묶기)는 다음 시간 몫이라, 여기선 .toList() 까지만 가요.
public class StreamComprehensive {

    // 1) 중복 없는 작성자 목록을 가나다순으로
    public static List<String> distinctAuthorsSorted(List<Post> posts) {
        return posts.stream()
                .map(Post::getAuthorName)
                .distinct()
                .sorted()
                .toList();
    }

    // 2) 좋아요 많은 상위 3개 글의 제목 — limit 으로 앞에서 N개만 잘라요(중간 연산).
    public static List<String> top3Titles(List<Post> posts) {
        return posts.stream()
                .sorted(Comparator.comparingInt(Post::getLikeCount).reversed())
                .limit(3)
                .map(Post::getContent)
                .toList();
    }

    // 3) 본문에서 중복 없는 해시태그만 모아 가나다순으로
    public static List<String> distinctHashtagsSorted(List<Post> posts) {
        return posts.stream()
                .flatMap(post -> Arrays.stream(post.getContent().split(" ")))
                .filter(word -> word.startsWith("#"))
                .distinct()
                .sorted()
                .toList();
    }

    // 4) 팔로워 1000명 이상 회원의 이름을 팔로워 많은 순으로
    public static List<String> influencerNames(List<Member> members) {
        return members.stream()
                .filter(member -> member.getFollowers() >= 1000)
                .sorted(Comparator.comparingInt(Member::getFollowers).reversed())
                .map(Member::getUsername)
                .toList();
    }

    // 5) 보관됨(ARCHIVED) 상태인 글의 내용만
    public static List<String> archivedContents(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getStatus() == PostStatus.ARCHIVED)
                .map(Post::getContent)
                .toList();
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member jaehoon = new Member("jaehoon", 1240, 42, 3, 200);
        Member seungwoo = new Member("seungwoo", 320, 12, 2, 60);

        Post archived = new Post("작년 여행 #여행", minji, 90);
        archived.setStatus(PostStatus.ARCHIVED);
        List<Post> posts = List.of(
                new Post("카페 #카페 #일상", minji, 250),
                new Post("코딩 #개발 #일상", jaehoon, 120),
                archived);

        System.out.println("작성자(가나다): " + distinctAuthorsSorted(posts));
        System.out.println("상위 3 제목: " + top3Titles(posts));
        System.out.println("해시태그(가나다): " + distinctHashtagsSorted(posts));
        System.out.println("인플루언서: " + influencerNames(List.of(minji, jaehoon, seungwoo)));
        System.out.println("보관 글: " + archivedContents(posts));
    }
}
