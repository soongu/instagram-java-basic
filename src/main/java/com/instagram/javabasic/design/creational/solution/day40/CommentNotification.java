package com.instagram.javabasic.design.creational.solution.day40;

// com/instagram/javabasic/design/creational/solution/day40/CommentNotification.java
// 댓글 알림 — 누가 댓글을 남겼는지 담아 문구를 만들어요.
public class CommentNotification implements Notification {

    private final String fromUser;

    public CommentNotification(String fromUser) {
        this.fromUser = fromUser;
    }

    @Override
    public String message() {
        return fromUser + " 님이 댓글을 남겼습니다.";
    }
}
