// com/instagram/javabasic/solution/day18/FollowUser.java
package com.instagram.javabasic.solution.day18;

/**
 * 과제 3: 팔로우 목록에 들어갈 사용자 한 명.
 * activeDays 는 "며칠 전에 활동했나" 라서, 작을수록 최근에 활동한 사람이다.
 */
class FollowUser {

    private final String username;
    private final int activeDays;

    FollowUser(String username, int activeDays) {
        this.username = username;
        this.activeDays = activeDays;
    }

    String getUsername() {
        return username;
    }

    int getActiveDays() {
        return activeDays;
    }

    @Override
    public String toString() {
        return "@" + username + "(" + activeDays + "일 전)";
    }
}
