package com.instagram.javabasic.solution.day15;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class Day15SolutionTest {

    @Test
    @DisplayName("과제1: 신고 사유 Enum 은 네 가지 상수를 가진다")
    void reportReason_hasFourReasons() {
        assertEquals(4, ReportReason.values().length);
        assertEquals("SPAM", ReportReason.SPAM.name());
    }

    @Test
    @DisplayName("과제2: 알림 종류마다 한글 이름과 소리 여부를 갖는다")
    void notificationType_hasDataPerConstant() {
        assertEquals("좋아요", NotificationType.LIKE.getDisplayName());
        assertEquals("팔로우", NotificationType.FOLLOW.getDisplayName());
        assertFalse(NotificationType.LIKE.playsSound());
        assertTrue(NotificationType.FOLLOW.playsSound());
    }

    @Test
    @DisplayName("과제3: 정렬 정책마다 같은 입력에 다른 점수를 낸다")
    void commentSortPolicy_scoresDifferently() {
        int likeCount = 5;
        int ageMinutes = 30;
        assertEquals(5, CommentSortPolicy.BEST.score(likeCount, ageMinutes));
        assertEquals(-30, CommentSortPolicy.NEWEST.score(likeCount, ageMinutes));
    }
}
