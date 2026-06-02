package com.instagram.javabasic.solution.day15;

// com/instagram/javabasic/solution/day15/NotificationType.java
// [응용] 과제 2 예시답안 — 알림 종류마다 다른 데이터(한글 이름·소리 여부)와 행동을 담은 Enum.
// Step 4(Grade, 데이터 달기)와 Step 5(PostStatus, 메서드 달기)를 섞은 형태예요.
public enum NotificationType {
    LIKE("좋아요", false),
    COMMENT("댓글", false),
    FOLLOW("팔로우", true);

    private final String displayName;
    private final boolean makesSound;

    NotificationType(String displayName, boolean makesSound) {
        this.displayName = displayName;
        this.makesSound = makesSound;
    }

    public String getDisplayName() {
        return displayName;
    }

    // 이 알림이 소리를 낼 수 있는지 알려줘요.
    public boolean playsSound() {
        return makesSound;
    }
}
