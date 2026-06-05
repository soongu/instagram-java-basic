package com.instagram.javabasic.collection;

// com/instagram/javabasic/collection/SortableMember.java
// "정렬할 수 있는 회원" 이에요. Comparable<SortableMember> 약속을 지키면,
// Collections.sort(list) 한 줄로 명단을 자동 정렬할 수 있어요.
// 정렬 기준은 compareTo 가 정해요. 여기서는 팔로워 수가 적은 사람부터(오름차순) 줄을 세워요.
public class SortableMember implements Comparable<SortableMember> {

    private final String username;
    private final int followers;

    public SortableMember(String username, int followers) {
        this.username = username;
        this.followers = followers;
    }

    public String getUsername() {
        return username;
    }

    public int getFollowers() {
        return followers;
    }

    // compareTo — "나를 other 와 비교하면 누가 앞이냐" 를 알려줘요.
    //   음수면 내가 앞, 0이면 같은 자리, 양수면 other 가 앞.
    // 팔로워 수가 적은 사람이 앞에 오도록(오름차순) 내 값에서 상대 값을 빼요.
    @Override
    public int compareTo(SortableMember other) {
        return this.followers - other.followers;
    }

    @Override
    public String toString() {
        return "@" + username + "(" + followers + ")";
    }
}
