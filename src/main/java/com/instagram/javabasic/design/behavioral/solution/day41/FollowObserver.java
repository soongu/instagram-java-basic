package com.instagram.javabasic.design.behavioral.solution.day41;

// com/instagram/javabasic/design/behavioral/solution/day41/FollowObserver.java
// 팔로우 사건을 구독하는 쪽이 지켜야 할 약속이에요.
// 누군가 팔로우하면 사용자(FollowableUser)가 이 메서드를 불러서 알려줘요.
// 알림을 받고 무엇을 할지는 약속을 지키는 각 구현 클래스가 알아서 정해요.

public interface FollowObserver {

    // 팔로우가 일어나면 호출돼요. followedBy 는 팔로우를 누른 사람 이름이에요.
    void onFollowed(String followedBy);
}
