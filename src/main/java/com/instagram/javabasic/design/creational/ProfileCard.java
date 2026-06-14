package com.instagram.javabasic.design.creational;

// com/instagram/javabasic/design/creational/ProfileCard.java
// 프로필 카드 한 장 — 사용자 이름은 꼭 있어야 하고, 소개글·웹사이트·인증 배지 같은 건
// 있을 수도 없을 수도 있어요. 이렇게 "필수 1개 + 선택 여러 개" 인 객체를 생성자 하나로 만들면
// new ProfileCard("jaehoon", null, null, true, 0) 처럼 빈칸과 순서가 헷갈려요.
// 그래서 재료를 하나씩 이름표 붙여 넣고 마지막에 build() 로 완성하는 "조립 도우미"(Builder)를 둬요.
// 완성된 카드는 필드가 final 이라 한 번 만들어지면 바뀌지 않아요(불변).
public class ProfileCard {

    private final String username;    // 필수 — 사용자 이름
    private final String bio;         // 선택 — 소개글
    private final String website;     // 선택 — 웹사이트 주소
    private final boolean verified;   // 선택 — 공식 인증 배지
    private final int followerCount;  // 선택 — 팔로워 수

    // 생성자는 private 이에요 — 바깥에서 new 로 직접 못 만들게 막고, 오직 Builder 로만 만들게 해요.
    private ProfileCard(Builder builder) {
        this.username = builder.username;
        this.bio = builder.bio;
        this.website = builder.website;
        this.verified = builder.verified;
        this.followerCount = builder.followerCount;
    }

    // 조립을 시작하는 입구 — ProfileCard.builder("jaehoon") 처럼 필수 값으로 시작해요.
    public static Builder builder(String username) {
        return new Builder(username);
    }

    public String getUsername() {
        return username;
    }

    public String getBio() {
        return bio;
    }

    public String getWebsite() {
        return website;
    }

    public boolean isVerified() {
        return verified;
    }

    public int getFollowerCount() {
        return followerCount;
    }

    // ===== 조립 도우미 — 재료를 하나씩 받아 쌓아두었다가 build() 에서 한 번에 카드로 굳혀요 =====
    public static class Builder {

        private final String username;  // 필수 — 입구에서 미리 받아요
        private String bio = "";         // 선택들은 기본값을 미리 둬서, 안 넣으면 이 값이 그대로 쓰여요
        private String website = "";
        private boolean verified = false;
        private int followerCount = 0;

        private Builder(String username) {
            this.username = username;
        }

        // 각 메서드는 값을 채운 뒤 자기 자신(this)을 돌려줘요 —
        // 그래서 .bio(...).website(...) 처럼 점을 찍어 줄줄이 이어 쓸 수 있어요.
        public Builder bio(String bio) {
            this.bio = bio;
            return this;
        }

        public Builder website(String website) {
            this.website = website;
            return this;
        }

        public Builder verified(boolean verified) {
            this.verified = verified;
            return this;
        }

        public Builder followerCount(int followerCount) {
            this.followerCount = followerCount;
            return this;
        }

        // 쌓아둔 재료로 완성된(그리고 더는 안 바뀌는) 카드를 만들어 줘요.
        public ProfileCard build() {
            return new ProfileCard(this);
        }
    }
}
