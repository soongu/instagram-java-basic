package com.instagram.javabasic.design.srp;

// com/instagram/javabasic/design/srp/Profile.java
// 좋은 예 (2/2) — "프로필 표시" 라는 한 가지 일만 맡아요.
// BigMember 에 뒤섞여 있던 책임 중에서 화면에 보여줄 문구를 만드는 부분만 떼어냈어요.
// 프로필 카드 디자인이 바뀌어도 인증(Account)은 전혀 건드리지 않아요.
public class Profile {

    private String username;
    private String bio;
    private int followerCount;

    public Profile(String username, String bio, int followerCount) {
        this.username = username;
        this.bio = bio;
        this.followerCount = followerCount;
    }

    // 이 클래스의 단 하나의 책임 — 프로필 카드에 보여줄 한 줄을 만들어요
    public String formatProfileCard() {
        return "@" + username + " | " + bio + " | 팔로워 " + followerCount;
    }

    public int getFollowerCount() {
        return followerCount;
    }
}
