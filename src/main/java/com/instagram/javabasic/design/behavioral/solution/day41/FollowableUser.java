package com.instagram.javabasic.design.behavioral.solution.day41;

// com/instagram/javabasic/design/behavioral/solution/day41/FollowableUser.java
// 팔로우를 받을 수 있는 사용자예요. 자기에게 일어난 일을 관찰자들에게 알려주는 쪽이에요.
// 누가 구독하는지는 알지만, 그들이 무슨 일을 하는지는 신경 쓰지 않아요.
// 그래서 새 관찰자가 늘어나도 이 클래스는 그대로 둬도 돼요.

import java.util.ArrayList;
import java.util.List;

public class FollowableUser {

    // 이 사용자의 팔로우 사건을 구독 중인 관찰자들이에요.
    private final List<FollowObserver> observers = new ArrayList<>();

    // 관찰자를 구독 목록에 등록해요.
    public void subscribe(FollowObserver observer) {
        observers.add(observer);
    }

    // 관찰자를 구독 목록에서 빼요. 그 뒤로는 더 이상 통지받지 않아요.
    public void unsubscribe(FollowObserver observer) {
        observers.remove(observer);
    }

    // 누군가 팔로우했어요. 등록된 모든 관찰자에게 한 명씩 알려줘요.
    public void follow(String followedBy) {
        for (FollowObserver observer : observers) {
            observer.onFollowed(followedBy);
        }
    }
}
