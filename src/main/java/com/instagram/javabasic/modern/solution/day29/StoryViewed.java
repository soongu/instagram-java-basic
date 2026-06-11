package com.instagram.javabasic.modern.solution.day29;

// com/instagram/javabasic/modern/solution/day29/StoryViewed.java
// 스토리 조회 사건 — 누가(viewer) 내 스토리를 봤는지 담아요.
public record StoryViewed(String viewer) implements FeedEvent {
}
