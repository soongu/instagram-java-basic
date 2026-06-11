package com.instagram.javabasic.modern.solution.day29;

// com/instagram/javabasic/modern/solution/day29/Mentioned.java
// 언급 사건 — 누가(actor) 어떤 글(postTitle)에서 나를 언급했는지 담아요.
public record Mentioned(String actor, String postTitle) implements FeedEvent {
}
