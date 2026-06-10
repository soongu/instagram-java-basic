package com.instagram.javabasic.exceptionbasic.solution.day22;

// com/instagram/javabasic/exceptionbasic/solution/day22/LikeCountValidator.java
// 지난 시간엔 이미 던져진 예외를 "잡는" 연습을 했어요.
// 이번엔 반대로, 잘못된 값이 들어오면 우리가 직접 예외를 "던지는" 연습이에요.
// 좋아요 수는 음수가 될 수 없어요. 그런데 어딘가에서 -5 같은 값이 흘러 들어오면,
// 그냥 두면 나중에 엉뚱한 곳에서 이상한 결과가 나와요.
// 그래서 값이 들어오는 입구에서 미리 검사하고, 음수면 그 자리에서 예외를 던져
// "여기서부터 잘못됐어요" 라고 분명히 알려주는 게 더 안전해요.
public class LikeCountValidator {

    // 좋아요 수 검증 — 0 이상이면 그 값을 그대로 돌려주고, 음수면 예외를 던져요.
    // throw new IllegalArgumentException(...) 이 실행되면 그 자리에서 메서드가 멈추고,
    // 예외가 이 메서드를 부른 쪽으로 전달돼요(전파).
    // 부른 쪽이 try-catch 로 받지 않으면 프로그램이 그대로 멈춰요.
    public int validateLikeCount(int likeCount) {
        if (likeCount < 0) {
            throw new IllegalArgumentException("좋아요 수는 음수일 수 없어요: " + likeCount);
        }
        return likeCount;
    }

    public static void main(String[] args) {
        LikeCountValidator validator = new LikeCountValidator();

        // 정상 값 — 검사를 통과해서 그 값이 그대로 나와요.
        int valid = validator.validateLikeCount(100);
        System.out.println("정상 좋아요 수: " + valid);

        // 음수 값 — 검사에 걸려서 예외가 던져져요. try-catch 로 받아 메시지를 출력해요.
        try {
            validator.validateLikeCount(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("검증 실패: " + e.getMessage());
        }

        // 위에서 예외를 잡았으니 프로그램은 죽지 않고 이 줄까지 실행돼요.
        System.out.println("끝까지 잘 실행됐어요!");
    }
}
