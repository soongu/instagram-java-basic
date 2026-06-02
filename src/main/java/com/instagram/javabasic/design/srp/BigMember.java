package com.instagram.javabasic.design.srp;

// com/instagram/javabasic/design/srp/BigMember.java
// 나쁜 예 — 한 클래스가 너무 많은 일을 떠안았어요.
// 계정 인증(username·password·checkPassword)과 프로필 정보(bio·followerCount),
// 추천 점수 계산(calculateScore), 화면 표시 문구 포맷(formatProfileCard)까지
// 전부 한 클래스 안에 뒤섞여 있어요. 동작은 정상이지만,
// "이 클래스는 무슨 일을 하는 클래스인가요?" 라고 물으면 한마디로 답하기 어려워요.
// 이렇게 책임이 너무 많은 상태를 다음 좋은 예에서 둘로 나눠볼 거예요.
public class BigMember {

    private String username;
    private String password;   // 비밀번호는 외부에서 직접 못 읽게 숨겨요
    private String bio;
    private int followerCount;

    public BigMember(String username, String password, String bio, int followerCount) {
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.followerCount = followerCount;
    }

    // 책임 1: 계정 인증 — 입력한 비밀번호가 맞는지 확인해요
    public boolean checkPassword(String input) {
        return password.equals(input);
    }

    // 책임 2: 추천 점수 계산 — 팔로워 100명당 1점
    public int calculateScore() {
        return followerCount / 100;
    }

    // 책임 3: 화면 표시 문구 포맷 — 프로필 카드에 보여줄 한 줄을 만들어요
    public String formatProfileCard() {
        return "@" + username + " | " + bio + " | 팔로워 " + followerCount;
    }
}
