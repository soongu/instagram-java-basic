package com.instagram.javabasic.modern.solution.day29;

// com/instagram/javabasic/modern/solution/day29/Tagged.java
// 태그 사건 — 누가(actor) 어떤 사진(photoTitle)에 나를 태그했는지 담아요.
public record Tagged(String actor, String photoTitle) implements FeedEvent {
}
