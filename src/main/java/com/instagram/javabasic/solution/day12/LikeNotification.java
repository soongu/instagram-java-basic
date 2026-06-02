package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/LikeNotification.java
// 과제 2 — 좋아요 알림이에요. Notification 을 물려받아 공통 정보는 그대로 쓰고,
// "누가 좋아요를 눌렀는지(행위자)" 만 더한 뒤 부모의 빈칸 message() 를 좋아요에 맞게 채워요.
public class LikeNotification extends Notification {

    // 좋아요 알림만 추가로 갖는 정보 — 좋아요를 누른 사람
    private String actorName;

    // 생성자 — super(...) 로 공통 필드를 먼저 채우고, 행위자를 채워요.
    public LikeNotification(String receiverName, String createdAgo, String actorName) {
        super(receiverName, createdAgo);
        this.actorName = actorName;
    }

    // 부모의 빈칸을 채워요 — 좋아요 알림 문구
    @Override
    public String message() {
        return actorName + "님이 회원님의 게시물을 좋아합니다";
    }
}
