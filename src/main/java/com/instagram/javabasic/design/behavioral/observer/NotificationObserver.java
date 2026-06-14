package com.instagram.javabasic.design.behavioral.observer;

// com/instagram/javabasic/design/behavioral/observer/NotificationObserver.java
// 좋아요 사건이 날 때마다 보여줄 알림 문구를 만들어 두는 관찰자예요.
// 같은 사건을 받아도 자기 방식(문구 만들기)으로 반응해요.
public class NotificationObserver implements PostObserver {

    private String lastMessage;

    @Override
    public void onLiked(String postId, String likedBy) {
        lastMessage = likedBy + "님이 회원님의 게시물을 좋아합니다";
    }

    // 가장 최근에 만든 알림 문구를 돌려줘요.
    public String getLastMessage() {
        return lastMessage;
    }
}
