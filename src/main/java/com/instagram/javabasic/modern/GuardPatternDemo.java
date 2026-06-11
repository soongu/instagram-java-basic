package com.instagram.javabasic.modern;

import java.util.List;

import com.instagram.javabasic.modern.notification.CommentNotification;
import com.instagram.javabasic.modern.notification.FollowNotification;
import com.instagram.javabasic.modern.notification.LikeNotification;
import com.instagram.javabasic.modern.notification.Notification;

// com/instagram/javabasic/modern/GuardPatternDemo.java
// 지난 시간 switch 패턴 매칭은 "타입" 으로만 갈랐어요(좋아요냐, 댓글이냐, 팔로우냐).
// 이번엔 when 가드를 붙여 "타입 + 조건" 으로 더 잘게 갈라요.
// 같은 LikeNotification 이라도 인기 게시물(노을 사진)이면 다른 문장으로 안내해요.
public class GuardPatternDemo {

    // 가드 없는 버전 — 타입만 보고 한 가지 문장으로 안내해요.
    public static String basic(Notification n) {
        return switch (n) {
            case LikeNotification l -> l.actor() + "님이 좋아요를 눌렀어요.";
            case CommentNotification c -> c.actor() + "님이 댓글을 남겼어요.";
            case FollowNotification f -> f.actor() + "님이 팔로우했어요.";
        };
    }

    // 가드 있는 버전 — when 뒤 조건이 참일 때만 그 case 로 가요.
    // 같은 타입이라도 위쪽 case 가 먼저 걸리면 그쪽으로 빠져요(위에서 아래 순서로 검사).
    public static String detailed(Notification n) {
        return switch (n) {
            // 인기 글("노을 사진")에 달린 좋아요는 따로 강조해요.
            case LikeNotification l when l.postTitle().equals("노을 사진") ->
                    "🔥 인기 게시물! " + l.actor() + "님도 '" + l.postTitle() + "' 을 좋아해요.";
            case LikeNotification l ->
                    l.actor() + "님이 '" + l.postTitle() + "' 을 좋아해요.";
            // 댓글 내용이 길면(20자 이상) "정성 댓글" 로 따로 안내해요.
            case CommentNotification c when c.text().length() >= 20 ->
                    "💬 정성 댓글! " + c.actor() + "님: " + c.text();
            case CommentNotification c ->
                    c.actor() + "님 댓글: " + c.text();
            case FollowNotification f ->
                    f.actor() + "님이 팔로우했어요.";
        };
    }

    public static void main(String[] args) {
        List<Notification> inbox = List.of(
                new LikeNotification("jaehoon", "노을 사진"),
                new LikeNotification("dana", "강아지 사진"),
                new CommentNotification("minji", "노을 사진", "색감이 정말 너무 예뻐서 저도 모르게 저장 버튼을 눌렀어요"),
                new CommentNotification("suho", "강아지 사진", "귀여워요"),
                new FollowNotification("yuna"));

        System.out.println("=== 가드 없는 분기 ===");
        for (Notification n : inbox) {
            System.out.println(basic(n));
        }

        System.out.println("=== 가드(when) 있는 분기 ===");
        for (Notification n : inbox) {
            System.out.println(detailed(n));
        }
    }
}
