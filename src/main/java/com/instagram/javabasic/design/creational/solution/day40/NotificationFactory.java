package com.instagram.javabasic.design.creational.solution.day40;

// com/instagram/javabasic/design/creational/solution/day40/NotificationFactory.java
// 종류에 맞는 알림 객체를 만들어주는 공장이에요.
// 알림을 보내는 쪽은 종류만 알려주면 되고, 어떤 클래스를 new 할지는 이 공장이 정해요.
// 새 종류(예: 멘션 알림)가 생겨도 고치는 곳은 이 공장 한 곳뿐이에요.
public class NotificationFactory {

    public enum NotificationType {
        LIKE, COMMENT, FOLLOW
    }

    public static Notification create(NotificationType type, String fromUser) {
        return switch (type) {
            case LIKE -> new LikeNotification(fromUser);
            case COMMENT -> new CommentNotification(fromUser);
            case FOLLOW -> new FollowNotification(fromUser);
        };
    }
}
