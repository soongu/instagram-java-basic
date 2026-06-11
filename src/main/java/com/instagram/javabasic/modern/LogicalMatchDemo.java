package com.instagram.javabasic.modern;

import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

// com/instagram/javabasic/modern/LogicalMatchDemo.java
// 흐름을 "판단" 하는 최종 연산 — 결과가 true/false 하나로 나와요.
// anyMatch(하나라도?) · allMatch(전부?) · noneMatch(아무도 안?) 세 가지를 익혀요.
public class LogicalMatchDemo {

    // anyMatch — 팔로워 1000명 이상인 인플루언서가 한 명이라도 있나요?
    public static boolean hasInfluencer(List<Member> members) {
        return members.stream()
                .anyMatch(member -> member.getFollowers() >= 1000);
    }

    // allMatch — 모든 글이 공개 상태인가요?
    public static boolean allPublic(List<Post> posts) {
        return posts.stream()
                .allMatch(post -> post.getStatus() == PostStatus.PUBLIC);
    }

    // noneMatch — 보관된 글이 하나도 없나요?
    public static boolean noArchived(List<Post> posts) {
        return posts.stream()
                .noneMatch(post -> post.getStatus() == PostStatus.ARCHIVED);
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        Member seungwoo = new Member("seungwoo", 320, 12, 2, 60);
        Post archived = new Post("작년 사진", minji, 90);
        archived.setStatus(PostStatus.ARCHIVED);
        List<Post> posts = List.of(new Post("오늘 카페", minji, 250), archived);

        System.out.println("인플루언서 있나? " + hasInfluencer(List.of(minji, seungwoo))); // true
        System.out.println("전부 공개? " + allPublic(posts));                              // false
        System.out.println("보관 글 없나? " + noArchived(posts));                          // false
    }
}
