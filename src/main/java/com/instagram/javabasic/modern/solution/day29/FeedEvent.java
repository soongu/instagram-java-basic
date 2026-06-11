package com.instagram.javabasic.modern.solution.day29;

// com/instagram/javabasic/modern/solution/day29/FeedEvent.java
// [과제 3] 피드에서 일어나는 사건을 sealed 로 정해요. 딱 세 종류만 허용해요.
public sealed interface FeedEvent
        permits StoryViewed, Mentioned, Tagged {
}
