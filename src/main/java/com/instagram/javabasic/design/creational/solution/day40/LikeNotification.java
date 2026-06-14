package com.instagram.javabasic.design.creational.solution.day40;

// com/instagram/javabasic/design/creational/solution/day40/LikeNotification.java
// 좋아요 알림 — 누가 좋아요를 눌렀는지 담아 문구를 만들어요.
public class LikeNotification implements Notification {

    private final String fromUser;

    public LikeNotification(String fromUser) {
        this.fromUser = fromUser;
    }

    @Override
    public String message() {
        return fromUser + " 님이 회원님의 게시물을 좋아합니다.";
    }
}
