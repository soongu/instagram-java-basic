package com.instagram.javabasic.modern.notification;

// com/instagram/javabasic/modern/notification/LikeNotification.java
// 좋아요 알림 — 누가(actor) 어떤 글(postTitle)을 좋아했는지 담아요.
// record 라서 값만 적으면 되고, implements 로 "나는 Notification 의 한 종류" 라고 밝혀요.
public record LikeNotification(String actor, String postTitle) implements Notification {
}
