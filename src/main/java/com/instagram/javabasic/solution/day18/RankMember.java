// com/instagram/javabasic/solution/day18/RankMember.java
package com.instagram.javabasic.solution.day18;

/**
 * 과제 2: 랭킹에 오를 회원 한 명.
 * Comparable 을 구현해서 "팔로워가 많은 순서(내림차순)" 가 기본 정렬이 되도록 한다.
 */
class RankMember implements Comparable<RankMember> {

    private final String username;
    private final int followers;

    RankMember(String username, int followers) {
        this.username = username;
        this.followers = followers;
    }

    String getUsername() {
        return username;
    }

    int getFollowers() {
        return followers;
    }

    /** 팔로워가 많은 쪽이 앞으로 오도록 (내림차순) 비교한다. */
    @Override
    public int compareTo(RankMember other) {
        return other.followers - this.followers;
    }

    @Override
    public String toString() {
        return "@" + username + "(" + followers + ")";
    }
}
