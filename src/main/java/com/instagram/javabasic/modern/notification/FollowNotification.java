package com.instagram.javabasic.modern.notification;

// com/instagram/javabasic/modern/notification/FollowNotification.java
// 팔로우 알림 — 누가(actor) 회원님을 팔로우했는지 담아요.
public record FollowNotification(String actor) implements Notification {
}
