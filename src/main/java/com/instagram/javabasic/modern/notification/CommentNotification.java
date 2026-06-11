package com.instagram.javabasic.modern.notification;

// com/instagram/javabasic/modern/notification/CommentNotification.java
// 댓글 알림 — 누가, 어떤 글에, 무슨 댓글을 남겼는지 담아요.
public record CommentNotification(String actor, String postTitle, String text) implements Notification {
}
