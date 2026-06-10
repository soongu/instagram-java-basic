package com.instagram.javabasic.modern;

import java.util.ArrayList;
import java.util.List;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;
import com.instagram.javabasic.domain.post.PostStatus;

// com/instagram/javabasic/modern/StreamIntro.java
// 지난 시간(Day 25) 우리가 손으로 만든 PostFilter.filter — for + 빈 리스트 + add 뼈대 —
// 의 "정식 버전" 이 바로 Stream 이에요.
// 같은 일(공개 게시물만 거르기)을 옛 방식(for)과 새 방식(stream)으로 나란히 해보면서,
// "내가 만든 게 이미 자바에 들어 있었구나" 를 확인해요.
public class StreamIntro {

    // [Before] Day 25 방식 — 빈 리스트를 만들고 for 로 훑으며 조건 맞는 것만 add 해요.
    public static List<Post> publicPostsByFor(List<Post> posts) {
        List<Post> result = new ArrayList<>();
        for (Post post : posts) {
            if (post.getStatus() == PostStatus.PUBLIC) {
                result.add(post);
            }
        }
        return result;
    }

    // [After] Stream 방식 — 컬렉션을 흐름(stream)으로 바꿔 .filter() 로 거르고 .toList() 로 다시 모아요.
    // 한 줄에 "무엇을 할지(공개만 거른다)" 만 적혀서, "어떻게 도는지(for·인덱스)" 가 사라졌어요.
    public static List<Post> publicPostsByStream(List<Post> posts) {
        return posts.stream()
                .filter(post -> post.getStatus() == PostStatus.PUBLIC)
                .toList();
    }

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);

        List<Post> posts = new ArrayList<>();
        posts.add(new Post("오늘 카페 다녀왔어요", minji, 250));
        Post secret = new Post("비밀 일기", minji, 5);
        secret.setStatus(PostStatus.PRIVATE);
        posts.add(secret);
        posts.add(new Post("새 운동화 자랑", minji, 80));

        System.out.println("[for 방식] 공개 글 수: " + publicPostsByFor(posts).size());      // 2
        System.out.println("[stream 방식] 공개 글 수: " + publicPostsByStream(posts).size()); // 2
        System.out.println("두 방식 결과가 같나요? "
                + publicPostsByFor(posts).equals(publicPostsByStream(posts)));               // true
    }
}
