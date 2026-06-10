package com.instagram.javabasic.exceptionbasic.solution.day23;

// com/instagram/javabasic/exceptionbasic/solution/day23/LikeCountValidator.java
// [기초] 과제 1 — 좋아요 수를 검증하는 메서드예요.
// 지난 시간엔 음수일 때 표준 IllegalArgumentException 을 던졌는데,
// 이번엔 직접 만든 InvalidLikeCountException 을 던져요. 검사 규칙은 그대로지만,
// 던지는 예외의 "이름" 이 사고를 설명한다는 점이 달라졌어요.
public class LikeCountValidator {

    // 좋아요 수 검증 — 0 이상이면 그대로 돌려주고, 음수면 커스텀 예외를 던져요.
    public int validateLikeCount(int likeCount) {
        if (likeCount < 0) {
            throw new InvalidLikeCountException("좋아요 수는 0 이상이어야 해요: " + likeCount);
        }
        return likeCount;
    }

    public static void main(String[] args) {
        LikeCountValidator validator = new LikeCountValidator();

        // 정상 값 — 검사를 통과해서 그 값이 그대로 나와요.
        int valid = validator.validateLikeCount(100);
        System.out.println("정상 좋아요 수: " + valid);

        // 음수 값 — 커스텀 예외가 던져져요. 이제 catch 도 그 예외 타입으로 받아요.
        try {
            validator.validateLikeCount(-5);
        } catch (InvalidLikeCountException e) {
            System.out.println("InvalidLikeCountException 발생: " + e.getMessage());
        }

        // 위에서 예외를 잡았으니 프로그램은 죽지 않고 이 줄까지 실행돼요.
        System.out.println("끝까지 잘 실행됐어요!");
    }
}
