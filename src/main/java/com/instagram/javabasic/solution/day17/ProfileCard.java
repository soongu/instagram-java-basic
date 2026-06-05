package com.instagram.javabasic.solution.day17;

// com/instagram/javabasic/solution/day17/ProfileCard.java
// [기초] 과제 1 — 프로필 카드 한 줄 만들기
// 화면에서 문자열로 넘어온 게시물 수·팔로워 수를 래퍼 클래스로 진짜 숫자로 바꾼 뒤,
// formatted 로 "@이름 · 게시물 N · 팔로워 N" 한 줄을 찍어내요.
public class ProfileCard {

    // 문자열로 들어온 두 숫자를 int 로 바꿔 깔끔한 한 줄로 조립해요.
    public static String render(String username, String postsRaw, String followersRaw) {
        int posts = Integer.parseInt(postsRaw);
        int followers = Integer.parseInt(followersRaw);
        return "@%s · 게시물 %d · 팔로워 %d".formatted(username, posts, followers);
    }

    public static void main(String[] args) {
        System.out.println(render("jaehoon_dev", "42", "1240"));
        // @jaehoon_dev · 게시물 42 · 팔로워 1240
    }
}
