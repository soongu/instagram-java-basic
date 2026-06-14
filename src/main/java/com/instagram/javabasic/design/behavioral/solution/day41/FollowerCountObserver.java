package com.instagram.javabasic.design.behavioral.solution.day41;

// com/instagram/javabasic/design/behavioral/solution/day41/FollowerCountObserver.java
// 팔로우가 일어날 때마다 팔로워 수를 한 명씩 세는 관찰자예요.
// 사용자는 이 관찰자가 "세는 일" 을 한다는 걸 몰라도 돼요. 그저 사건만 알려주면 돼요.

public class FollowerCountObserver implements FollowObserver {

    // 지금까지 통지받은 팔로우 횟수예요.
    private int count = 0;

    @Override
    public void onFollowed(String followedBy) {
        // 누가 팔로우했는지는 신경 쓰지 않고, 횟수만 하나 올려요.
        count++;
    }

    public int getCount() {
        return count;
    }
}
