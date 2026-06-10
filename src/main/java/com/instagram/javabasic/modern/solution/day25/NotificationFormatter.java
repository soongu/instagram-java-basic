package com.instagram.javabasic.modern.solution.day25;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.domain.post.Post;

// [중] 과제 2 예시답안 — "바꾸는 일"(Function)과 "출력하는 일"(Consumer)을 따로 담았어요.
// 나중에 출력 대신 다른 처리로 바꾸고 싶으면 printer 만 갈아 끼우면 돼요.
public class NotificationFormatter {

    // Function — 게시물을 받아 알림 문구 문자열로 바꿔요
    public static final Function<Post, String> TO_MESSAGE =
            p -> "[알림] @" + p.getAuthorName() + " 님의 새 글: " + p.getContent()
                    + " (좋아요 " + p.getLikeCount() + ")";

    public static void main(String[] args) {
        Member minji = new Member("minji", 8500, 150, 5, 365);
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("첫 글이에요", minji, 30));
        posts.add(new Post("오늘의 인기 글", minji, 250));
        posts.add(new Post("소소한 일상", minji, 12));

        Function<Post, String> toMessage = TO_MESSAGE;   // 변환 그릇
        Consumer<String> printer = line -> System.out.println(line);   // 소비 그릇

        for (Post post : posts) {
            String message = toMessage.apply(post);   // 변환
            printer.accept(message);                  // 소비(출력)
        }
    }
}
