package com.instagram.javabasic.modern;

import java.util.List;

import com.instagram.javabasic.modern.notification.CommentNotification;
import com.instagram.javabasic.modern.notification.FollowNotification;
import com.instagram.javabasic.modern.notification.LikeNotification;
import com.instagram.javabasic.modern.notification.Notification;

// com/instagram/javabasic/modern/UnnamedVariableDemo.java
// 밑줄 하나 _ 는 "이 값은 안 쓸 거예요" 를 코드로 분명히 밝히는 이름 없는 변수예요.
// 안 쓰는 변수에 굳이 이름을 지어두면, 읽는 사람이 "이건 어디서 쓰지?" 하고 헷갈려요.
// _ 로 적으면 "받긴 받는데 버린다" 는 뜻이 한눈에 보여요.
public class UnnamedVariableDemo {

    // 1) switch 패턴에서 구조분해는 하지만 일부 값만 쓸 때 — 안 쓰는 자리를 _ 로 버려요.
    // 좋아요는 누가(actor) 눌렀는지만 쓰고, 어떤 글인지(postTitle)는 _ 로 버려요.
    public static String actorOf(Notification n) {
        return switch (n) {
            case LikeNotification(String actor, _) -> actor;
            case CommentNotification(String actor, _, _) -> actor;
            case FollowNotification(String actor) -> actor;
        };
    }

    public static void main(String[] args) {
        List<Notification> inbox = List.of(
                new LikeNotification("jaehoon", "노을 사진"),
                new CommentNotification("minji", "노을 사진", "예뻐요"),
                new FollowNotification("dana"));

        System.out.println("=== 알림 보낸 사람만 추출 (나머지는 _ 로 버림) ===");
        for (Notification n : inbox) {
            System.out.println(actorOf(n));
        }

        // 2) for-each 에서 값은 안 보고 횟수만 셀 때 — 꺼낸 원소를 _ 로 버려요.
        int count = 0;
        for (String _ : List.of("좋아요", "댓글", "팔로우")) {
            count++;
        }
        System.out.println("=== 알림 종류 수 (원소는 _ 로 버리고 개수만) ===");
        System.out.println(count + "종");

        // 3) catch 에서 예외 객체를 안 쓸 때 — 잡기만 하고 객체는 _ 로 버려요.
        String raw = "백사십";
        System.out.println("=== 숫자 변환 시도 (예외 객체는 _ 로 버림) ===");
        try {
            int followers = Integer.parseInt(raw);
            System.out.println("팔로워: " + followers);
        } catch (NumberFormatException _) {
            System.out.println("숫자가 아니라 변환에 실패했어요. 기본값 0 으로 둘게요.");
        }
    }
}
