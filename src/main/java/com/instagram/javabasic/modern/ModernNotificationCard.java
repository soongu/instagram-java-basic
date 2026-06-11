package com.instagram.javabasic.modern;

import java.util.List;

import com.instagram.javabasic.modern.notification.CommentNotification;
import com.instagram.javabasic.modern.notification.FollowNotification;
import com.instagram.javabasic.modern.notification.LikeNotification;
import com.instagram.javabasic.modern.notification.Notification;

// com/instagram/javabasic/modern/ModernNotificationCard.java
// 오늘 배운 것들을 한자리에 모아요:
//   - 텍스트 블록(""")  으로 화면용 카드를 그리고,
//   - switch + when 가드로 알림 종류·조건에 따라 다른 메시지를 만들고,
//   - 이름 없는 변수 _ 로 안 쓰는 값을 버리고,
//   - Stream(filter·map) 으로 알림 목록을 다뤄요.
//   - ProfileCard record 로 카드 머리글에 들어갈 프로필을 가볍게 담아요.
public class ModernNotificationCard {

    // 알림 하나를 한 줄 메시지로 — switch + when 가드 + 이름 없는 변수 _ 가 함께 일해요.
    public static String toLine(Notification n) {
        return switch (n) {
            // 인기 글에 달린 좋아요는 강조. 글 제목만 쓰고 actor 는 _ 로 버려요.
            case LikeNotification(_, String title) when title.equals("노을 사진") ->
                    "🔥 인기 글 '" + title + "' 에 좋아요가 쌓이고 있어요";
            // 그 외 좋아요는 누가 눌렀는지(actor)만 쓰고 제목은 _ 로 버려요.
            case LikeNotification(String actor, _) ->
                    "❤️ " + actor + "님이 좋아요를 눌렀어요";
            // 댓글은 보낸 사람과 내용을 쓰고, 가운데 글 제목은 _ 로 버려요.
            case CommentNotification(String actor, _, String text) ->
                    "💬 " + actor + "님: " + text;
            case FollowNotification(String actor) ->
                    "➕ " + actor + "님이 팔로우했어요";
        };
    }

    // 알림 목록 → 좋아요·댓글만 남긴 뒤(Stream filter) → 한 줄 메시지로 바꿔(map) 모아요.
    public static List<String> importantLines(List<Notification> inbox) {
        return inbox.stream()
                .filter(n -> !(n instanceof FollowNotification))
                .map(ModernNotificationCard::toLine)
                .toList();
    }

    // 프로필 + 알림 줄들을 텍스트 블록 한 장으로 그려요.
    public static String render(ProfileCard profile, List<String> lines) {
        String body = String.join("\n", lines);
        return """
                ┌─────────────────────────────┐
                  @%s (%s · 팔로워 %d명)
                ───────────────────────────────
                %s
                └─────────────────────────────┘""".formatted(
                profile.username(), profile.grade(), profile.followers(), body);
    }

    public static void main(String[] args) {
        ProfileCard profile = new ProfileCard("jaehoon", "인플루언서", 1240);

        List<Notification> inbox = List.of(
                new LikeNotification("dana", "노을 사진"),
                new LikeNotification("suho", "강아지 사진"),
                new CommentNotification("minji", "노을 사진", "색감 너무 예뻐요"),
                new FollowNotification("yuna"));

        List<String> lines = importantLines(inbox);
        System.out.println(render(profile, lines));
    }
}
