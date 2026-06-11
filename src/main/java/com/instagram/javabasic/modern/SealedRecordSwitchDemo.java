package com.instagram.javabasic.modern;

import java.util.List;

import com.instagram.javabasic.modern.notification.CommentNotification;
import com.instagram.javabasic.modern.notification.FollowNotification;
import com.instagram.javabasic.modern.notification.LikeNotification;
import com.instagram.javabasic.modern.notification.Notification;

// com/instagram/javabasic/modern/SealedRecordSwitchDemo.java
// sealed + record + switch 세 가지가 만나는 자리예요.
// switch 로 알림 종류별로 갈라서, 사람이 읽을 문장을 만들어요.
// Notification 이 sealed 라 자바가 "세 종류 다 처리했네" 를 확인해줘요 — default 가 필요 없어요.
public class SealedRecordSwitchDemo {

    // 알림 하나를 사람이 읽을 한 줄로 바꿔요.
    public static String toMessage(Notification n) {
        return switch (n) {
            // record 분해 — 괄호로 안의 값을 바로 꺼내 변수로 받아요.
            case LikeNotification(String actor, String title) ->
                    actor + "님이 '" + title + "' 글을 좋아해요.";
            case CommentNotification(String actor, String title, String text) ->
                    actor + "님이 '" + title + "' 에 댓글: " + text;
            case FollowNotification(String actor) ->
                    actor + "님이 회원님을 팔로우하기 시작했어요.";
        };
    }

    public static void main(String[] args) {
        List<Notification> inbox = List.of(
                new LikeNotification("jaehoon", "노을 사진"),
                new CommentNotification("minji", "노을 사진", "색감 예뻐요!"),
                new FollowNotification("dana"));

        for (Notification n : inbox) {
            System.out.println(toMessage(n));
        }
        // jaehoon님이 '노을 사진' 글을 좋아해요.
        // minji님이 '노을 사진' 에 댓글: 색감 예뻐요!
        // dana님이 회원님을 팔로우하기 시작했어요.
    }
}
