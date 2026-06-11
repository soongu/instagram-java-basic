package com.instagram.javabasic.modern.solution.day30;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.instagram.javabasic.modern.notification.CommentNotification;
import com.instagram.javabasic.modern.notification.FollowNotification;
import com.instagram.javabasic.modern.notification.LikeNotification;

class Day30SolutionTest {

    // ===== 과제 1: PostShareCard — 텍스트 블록 + .formatted =====

    @Test
    @DisplayName("과제1 shareCard: 제목·작성자·좋아요 수를 끼운 세 줄 카드")
    void shareCardFormatsThreeLines() {
        String card = PostShareCard.shareCard("노을 사진", "jaehoon", 1240);
        assertEquals("📷 노을 사진\nby @jaehoon\n❤️ 1240", card);
    }

    @Test
    @DisplayName("과제1 shareCard: 끝에 개행이 붙지 않는다(줄바꿈은 정확히 2개)")
    void shareCardHasNoTrailingNewline() {
        String card = PostShareCard.shareCard("강아지 사진", "dana", 0);
        assertEquals("📷 강아지 사진\nby @dana\n❤️ 0", card);
        assertEquals(2, card.chars().filter(c -> c == '\n').count());
    }

    // ===== 과제 2: NotificationPriority — switch + when + _ =====

    @Test
    @DisplayName("과제2 priority: 인기 글 좋아요는 3, 그 외 좋아요는 1")
    void priorityForLike() {
        assertEquals(3, NotificationPriority.priority(new LikeNotification("jaehoon", "노을 사진")));
        assertEquals(1, NotificationPriority.priority(new LikeNotification("dana", "강아지 사진")));
    }

    @Test
    @DisplayName("과제2 priority: 20자 이상 댓글은 2, 그 외 댓글은 1")
    void priorityForComment() {
        String longText = "색감이 정말 너무 예뻐서 저도 모르게 저장 버튼을 눌렀어요"; // 20자 이상
        assertEquals(2, NotificationPriority.priority(new CommentNotification("minji", "노을 사진", longText)));
        assertEquals(1, NotificationPriority.priority(new CommentNotification("suho", "강아지 사진", "귀여워요")));
    }

    @Test
    @DisplayName("과제2 priority: 길이 20 경계 — 19자는 1, 20자는 2")
    void priorityCommentBoundary() {
        String text19 = "가".repeat(19);
        String text20 = "가".repeat(20);
        assertEquals(1, NotificationPriority.priority(new CommentNotification("a", "글", text19)));
        assertEquals(2, NotificationPriority.priority(new CommentNotification("a", "글", text20)));
    }

    @Test
    @DisplayName("과제2 priority: 팔로우는 2")
    void priorityForFollow() {
        assertEquals(2, NotificationPriority.priority(new FollowNotification("yuna")));
    }

    // ===== 과제 3: RecentViewers — 순서 있는 컬렉션 =====

    @Test
    @DisplayName("과제3 firstViewer/latestViewer: 첫 번째와 마지막을 꺼낸다")
    void firstAndLatest() {
        List<String> viewers = List.of("jaehoon", "minji", "dana");
        assertEquals("jaehoon", RecentViewers.firstViewer(viewers));
        assertEquals("dana", RecentViewers.latestViewer(viewers));
    }

    @Test
    @DisplayName("과제3 recentOrder: 최신순으로 뒤집은 목록을 돌려준다")
    void recentOrderReverses() {
        List<String> viewers = List.of("jaehoon", "minji", "dana");
        assertEquals(List.of("dana", "minji", "jaehoon"), RecentViewers.recentOrder(viewers));
    }

    @Test
    @DisplayName("과제3 recentOrder: 원본 리스트는 망가지지 않는다")
    void recentOrderKeepsOriginalIntact() {
        List<String> viewers = new ArrayList<>(List.of("jaehoon", "minji", "dana"));
        RecentViewers.recentOrder(viewers);
        assertEquals(List.of("jaehoon", "minji", "dana"), viewers);
    }
}
