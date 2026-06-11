package com.instagram.javabasic.modern;

import com.instagram.javabasic.modern.notification.CommentNotification;
import com.instagram.javabasic.modern.notification.FollowNotification;
import com.instagram.javabasic.modern.notification.LikeNotification;
import com.instagram.javabasic.modern.notification.Notification;

// com/instagram/javabasic/modern/SealedNotificationDemo.java
// sealed interface Notification 을 구현한 세 가지 record 를 만들어봐요.
// 셋 다 "Notification 의 한 종류" 라서 같은 타입 변수에 담을 수 있어요(다형성).
public class SealedNotificationDemo {

    public static void main(String[] args) {
        Notification n1 = new LikeNotification("jaehoon", "노을 사진");
        Notification n2 = new CommentNotification("minji", "노을 사진", "색감 예뻐요!");
        Notification n3 = new FollowNotification("dana");

        // record 의 자동 toString 덕에 어떤 알림인지 한눈에 보여요.
        System.out.println(n1); // LikeNotification[actor=jaehoon, postTitle=노을 사진]
        System.out.println(n2); // CommentNotification[actor=minji, postTitle=노을 사진, text=색감 예뻐요!]
        System.out.println(n3); // FollowNotification[actor=dana]
    }
}
