package com.instagram.javabasic.modern.solution.day30;

import com.instagram.javabasic.modern.notification.CommentNotification;
import com.instagram.javabasic.modern.notification.FollowNotification;
import com.instagram.javabasic.modern.notification.LikeNotification;
import com.instagram.javabasic.modern.notification.Notification;

// com/instagram/javabasic/modern/solution/day30/NotificationPriority.java
// [과제 2] 알림마다 "얼마나 중요한지" 우선순위 점수를 매겨요.
// switch 패턴 매칭 + 가드(when) 로 "타입 + 조건" 을 함께 보고, 안 쓰는 값은 _ 로 비워요.
// Notification 이 sealed 라 default 없이도 세 종류를 빠짐없이 처리해요.
public class NotificationPriority {

    public static int priority(Notification n) {
        return switch (n) {
            // 인기 글("노을 사진")에 달린 좋아요는 더 중요해요 — 가드 붙은 갈래를 위에 둬요.
            case LikeNotification(_, String title) when title.equals("노을 사진") -> 3;
            // 그 외 좋아요 — actor·postTitle 둘 다 안 쓰니 통째로 _ 로 비워요.
            case LikeNotification _ -> 1;
            // 내용이 20자 이상인 정성 댓글 — text 만 보고 actor·postTitle 은 _ 로 비워요.
            case CommentNotification(_, _, String text) when text.length() >= 20 -> 2;
            // 그 외 댓글
            case CommentNotification _ -> 1;
            // 팔로우
            case FollowNotification _ -> 2;
        };
    }
}
