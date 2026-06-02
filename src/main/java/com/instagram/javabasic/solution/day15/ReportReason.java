package com.instagram.javabasic.solution.day15;

// com/instagram/javabasic/solution/day15/ReportReason.java
// [기본] 과제 1 예시답안 — 게시물 신고 사유를 Enum 으로.
// 스팸·부적절·사칭·기타 네 가지로 정해져 있으니, 문자열 대신 Enum 으로 안전하게 다뤄요.
public enum ReportReason {
    SPAM,
    INAPPROPRIATE,
    IMPERSONATION,
    ETC
}
