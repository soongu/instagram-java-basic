package com.instagram.javabasic.modern.notification;

// com/instagram/javabasic/modern/notification/Notification.java
// "알림" 이라는 역할을 sealed(봉인) 인터페이스로 정해요.
// permits 뒤에 적은 세 가지만 이 인터페이스를 구현할 수 있어요 — 아무나 못 끼어들어요.
// 추상 클래스 시간엔 자식이 얼마든 늘 수 있었는데, 여기선 "딱 이 셋" 으로 못 박아요.
public sealed interface Notification
        permits LikeNotification, CommentNotification, FollowNotification {
}
