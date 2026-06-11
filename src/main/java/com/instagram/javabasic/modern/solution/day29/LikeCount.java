package com.instagram.javabasic.modern.solution.day29;

// com/instagram/javabasic/modern/solution/day29/LikeCount.java
// [과제 2] 좋아요 수를 담는 record. 컴팩트 생성자로 "음수는 안 된다" 를 만드는 순간 막아요.
public record LikeCount(int value) {

    public LikeCount {
        if (value < 0) {
            throw new IllegalArgumentException("좋아요 수는 음수가 될 수 없어요: " + value);
        }
    }
}
