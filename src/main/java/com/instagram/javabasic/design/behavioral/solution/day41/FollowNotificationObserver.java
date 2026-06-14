package com.instagram.javabasic.design.behavioral.solution.day41;

// com/instagram/javabasic/design/behavioral/solution/day41/FollowNotificationObserver.java
// 팔로우가 일어날 때마다 알림 문구를 만들어 두는 관찰자예요.
// 같은 사건(팔로우)을 받지만, 횟수를 세는 관찰자와는 전혀 다른 일을 해요.

public class FollowNotificationObserver implements FollowObserver {

    // 가장 최근에 만든 알림 문구예요.
    private String lastMessage;

    @Override
    public void onFollowed(String followedBy) {
        // 팔로우한 사람 이름을 넣어 알림 문구를 만들어 둬요.
        lastMessage = followedBy + "님이 회원님을 팔로우하기 시작했습니다";
    }

    public String getLastMessage() {
        return lastMessage;
    }
}
