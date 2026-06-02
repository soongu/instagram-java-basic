package com.instagram.javabasic.enumbasic;

// com/instagram/javabasic/enumbasic/ReactionType.java
// 인스타 게시물에 누를 수 있는 "반응" 종류예요.
// 좋아요·하트·웃음·놀람·슬픔 — 가능한 값이 딱 이 다섯 개로 정해져 있죠.
// 이렇게 "정해진 선택지의 집합" 을 표현하는 게 바로 enum(열거형)이에요.
// 클래스처럼 생겼지만, 미리 정해둔 상수들만 값이 될 수 있어요.
public enum ReactionType {
    LIKE,
    LOVE,
    HAHA,
    WOW,
    SAD
}
