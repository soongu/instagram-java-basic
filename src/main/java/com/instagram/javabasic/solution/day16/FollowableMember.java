package com.instagram.javabasic.solution.day16;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/solution/day16/FollowableMember.java
// [응용] 과제 2 예시답안 — "나를 팔로우하는 사람들(팔로워)" 을 배열로 안고 있는 회원이에요.
// 기존 Member 는 "내가 팔로우하는 사람들(팔로잉)" 만 배열로 가지고 있었어요(following).
// 여기서는 방향을 반대로 돌려, "나를 팔로우하는 사람들" 을 모으는 묶음을 더해요.
// 기존 Member 는 그대로 두고 싶으니, Member 를 상속(extends)해서 기능만 덧붙여요.
// 부모(Member)의 필드는 모두 private 이라, 부모 생성자(super(...))로만 초기화할 수 있어요.
public class FollowableMember extends Member {

    // 팔로워 묶음의 처음 크기예요. 이만큼까지 담아요.
    private static final int CAPACITY = 16;

    // 나를 팔로우하는 사람들 — 배열에 모으고, 몇 명 찼는지 카운터로 세요(1:N).
    // 부모 Member 에 이미 int followers(팔로워 "수") 가 있어서, 이름 충돌과 혼동을 피하려고
    // 배열 이름은 followerMembers 로 따로 둬요. (사람들의 묶음 vs 단순 숫자)
    private Member[] followerMembers = new Member[CAPACITY];
    private int followerCount = 0;

    // 부모 Member 의 다섯 가지 정보를 받아 super(...) 로 그대로 위임해요.
    // 부모 필드는 private 이라, 우리가 직접 채울 수 없고 이 통로로만 채워요.
    public FollowableMember(String username, int followers, int posts, int mutualFriends, int daysActive) {
        super(username, followers, posts, mutualFriends, daysActive);
    }

    // 나를 팔로우하는 사람 하나를 묶음에 더해요. 자리가 차면 더 담지 않고 넘어가요.
    // 기존 Member.follow(팔로잉을 더하기) 와 방향만 반대일 뿐 구조는 똑같아요.
    public void addFollower(Member m) {
        if (followerCount >= followerMembers.length) {
            System.out.println("팔로워 묶음이 가득 찼어요. 더 담지 않아요.");
            return;
        }
        followerMembers[followerCount] = m;
        followerCount++;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    public Member getFollower(int index) {
        return followerMembers[index];
    }
}
