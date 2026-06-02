package com.instagram.javabasic.solution.day15;

// com/instagram/javabasic/solution/day15/Day15SolutionMain.java
// Day 15 과제 1~3 예시답안을 한 번에 실행해보는 main 이에요.
public class Day15SolutionMain {

    public static void main(String[] args) {
        // 과제 1 — 신고 사유 Enum
        ReportReason reason = ReportReason.SPAM;
        System.out.println("신고 사유: " + reason.name());
        System.out.println("신고 사유 종류 수: " + ReportReason.values().length);

        System.out.println();

        // 과제 2 — 알림 종류 Enum (데이터 + 메서드)
        for (NotificationType type : NotificationType.values()) {
            System.out.println(type.getDisplayName() + " 알림 — 소리: " + type.playsSound());
        }

        System.out.println();

        // 과제 3 — 댓글 정렬 정책 (상수별 메서드)
        int likeCount = 5;
        int ageMinutes = 30;
        System.out.println("좋아요순 점수: " + CommentSortPolicy.BEST.score(likeCount, ageMinutes));
        System.out.println("최신순 점수: " + CommentSortPolicy.NEWEST.score(likeCount, ageMinutes));
    }
}
