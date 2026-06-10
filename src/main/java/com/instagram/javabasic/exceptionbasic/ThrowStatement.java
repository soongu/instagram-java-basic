package com.instagram.javabasic.exceptionbasic;

// com/instagram/javabasic/exceptionbasic/ThrowStatement.java
// 지난 시간엔 터진 예외를 try-catch 로 "받는" 쪽을 배웠어요.
// 이번엔 반대로, 내가 직접 예외를 "던지는(throw)" 쪽이에요.
// 값이 규칙에 안 맞으면 그냥 넘어가지 않고 throw 로 "이건 잘못됐어요!" 하고 알려요.
//   throw new IllegalArgumentException("메시지");  ← 이 줄에서 메서드가 즉시 멈추고 예외가 위로 튀어요
// 캡션(게시물 글) 길이가 인스타 한도를 넘는지 직접 검사해 볼게요.
public class ThrowStatement {

    // 인스타 캡션 최대 길이예요. 2200자까지만 쓸 수 있어요.
    public static final int MAX_CAPTION_LENGTH = 2200;

    // 캡션을 검사해서 통과하면 그대로 돌려주고, 규칙에 어긋나면 throw 로 막아요.
    // 빈 캡션이거나 한도를 넘으면 IllegalArgumentException 을 던져요.
    public String validateCaption(String caption) {
        if (caption == null || caption.isEmpty()) {
            throw new IllegalArgumentException("캡션이 비어 있어요.");
        }
        if (caption.length() > MAX_CAPTION_LENGTH) {
            throw new IllegalArgumentException("캡션은 2200자까지예요. 지금은 " + caption.length() + "자.");
        }
        return caption;
    }

    public static void main(String[] args) {
        ThrowStatement validator = new ThrowStatement();

        // 정상 — 짧은 캡션은 그대로 통과해요.
        String ok = validator.validateCaption("오늘 점심 인증 #맛집");
        System.out.println("통과한 캡션: " + ok);

        // 사고 — 한도를 넘는 캡션은 throw 로 막혀요. try-catch 로 받아 메시지를 보여줘요.
        StringBuilder tooLong = new StringBuilder();
        for (int i = 0; i < MAX_CAPTION_LENGTH + 1; i++) {
            tooLong.append("가");
        }
        try {
            validator.validateCaption(tooLong.toString());
        } catch (IllegalArgumentException e) {
            System.out.println("막혔어요: " + e.getMessage());
        }
    }
}
