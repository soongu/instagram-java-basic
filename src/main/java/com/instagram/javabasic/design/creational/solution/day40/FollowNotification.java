package com.instagram.javabasic.design.creational.solution.day40;

// com/instagram/javabasic/design/creational/solution/day40/FollowNotification.java
// 팔로우 알림 — 누가 팔로우했는지 담아 문구를 만들어요.
public class FollowNotification implements Notification {

    private final String fromUser;

    public FollowNotification(String fromUser) {
        this.fromUser = fromUser;
    }

    @Override
    public String message() {
        return fromUser + " 님이 회원님을 팔로우하기 시작했습니다.";
    }
}
