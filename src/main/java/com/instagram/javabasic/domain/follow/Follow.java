package com.instagram.javabasic.domain.follow;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/domain/follow/Follow.java
// 팔로우 "관계" 한 건을 객체로 표현하는 클래스예요.
// 지금까지 한 사람(Member)은 알았지만, "누가 누구를 팔로우한다" 는 두 사람 사이의 연결이었어요.
// 그 연결 자체를 하나의 객체로 만들면, 관계에도 이름을 붙이고 따로 다룰 수 있어요.
// 한 번 맺어진 관계는 바뀌지 않으니 두 필드 모두 final 로 잠가 불변으로 만들어요.
public class Follow {

    // 팔로우를 거는 사람 (예: 내가 상대를 팔로우)
    private final Member follower;
    // 팔로우를 받는 사람 (예: 상대)
    private final Member followee;

    // 두 사람을 받아 관계를 완성해요. 한 번 정해지면 못 바꿔요.
    public Follow(Member follower, Member followee) {
        this.follower = follower;
        this.followee = followee;
    }

    public Member getFollower() {
        return follower;
    }

    public Member getFollowee() {
        return followee;
    }

    // 관계를 한눈에 보이게 — "@거는사람 → @받는사람" 형식으로 출력해요.
    @Override
    public String toString() {
        return "@" + follower.getUsername() + " → @" + followee.getUsername();
    }
}
