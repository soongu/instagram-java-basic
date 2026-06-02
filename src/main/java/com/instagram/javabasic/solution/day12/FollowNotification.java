package com.instagram.javabasic.solution.day12;

// com/instagram/javabasic/solution/day12/FollowNotification.java
// 과제 2 — 팔로우 알림이에요. Notification 을 물려받아 공통 정보(받는 사람·시점)는 그대로 쓰고,
// "누가 팔로우했는지(행위자)" 만 더한 뒤 부모의 빈칸 message() 를 팔로우에 맞게 채워요.
public class FollowNotification extends Notification {

    // 팔로우 알림만 추가로 갖는 정보 — 팔로우한 사람
    private String actorName;

    // 생성자 — super(...) 로 공통 필드를 먼저 채우고, 행위자를 채워요.
    public FollowNotification(String receiverName, String createdAgo, String actorName) {
        super(receiverName, createdAgo);
        this.actorName = actorName;
    }

    // 부모의 빈칸을 채워요 — 팔로우 알림 문구
    @Override
    public String message() {
        return actorName + "님이 회원님을 팔로우했습니다";
    }
}
