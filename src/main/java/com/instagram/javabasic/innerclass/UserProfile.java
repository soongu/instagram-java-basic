package com.instagram.javabasic.innerclass;

// com/instagram/javabasic/innerclass/UserProfile.java
// 프로필 화면 한 개를 표현해요. 사용자 이름과 "통계(Stats)" 를 함께 들고 있어요.
// 그런데 Stats 는 UserProfile 안에서만 의미가 있는 작은 묶음이라,
// 따로 파일을 만들기보다 클래스 안에 클래스로 넣었어요(중첩 클래스).
// static 을 붙이면 바깥 객체 없이도 UserProfile.Stats 로 바로 만들 수 있어요.
public class UserProfile {

    private final String username;
    private final Stats stats;

    public UserProfile(String username, Stats stats) {
        this.username = username;
        this.stats = stats;
    }

    public String getUsername() {
        return username;
    }

    public Stats getStats() {
        return stats;
    }

    // 정적 중첩 클래스 — 게시물 수와 팔로워 수를 한 묶음으로 담아요.
    // 바깥(UserProfile)의 인스턴스가 없어도 독립적으로 만들 수 있어요.
    public static class Stats {
        private final int postCount;
        private final int followerCount;

        public Stats(int postCount, int followerCount) {
            this.postCount = postCount;
            this.followerCount = followerCount;
        }

        public int getPostCount() {
            return postCount;
        }

        public int getFollowerCount() {
            return followerCount;
        }

        public String summary() {
            return "게시물 " + postCount + " · 팔로워 " + followerCount;
        }
    }
}
