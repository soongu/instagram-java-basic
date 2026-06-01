package com.instagram.javabasic.domain.member;

// com/instagram/javabasic/domain/member/Member.java
// 인스타 추천 사용자 한 명을 표현하는 클래스예요.
// 지난 시간엔 한 사람의 정보가 평행 배열 다섯 개에 흩어져 있었는데,
// 이제 그 다섯 가지를 한 객체 안에 묶어서 "한 명 = 한 덩어리" 로 다뤄요.
// 이번 단계에서는 데이터(필드)와 그것을 채우는 생성자만 만들어요.
// (행동을 담는 메서드는 다음 시간에 추가합니다.)
public class Member {

    // 한 사람을 이루는 다섯 가지 정보 — 이제 private 으로 숨겨 직접 접근을 막아요
    private String username;       // 사용자 이름
    private int followers;         // 팔로워 수
    private int posts;             // 게시물 수
    private int mutualFriends;     // 함께 아는 친구 수
    private int daysActive;        // 활동 일수

    // 기본 생성자 — 아무 값도 받지 않고 빈 객체를 만들어요.
    // 이때 필드는 각 타입의 기본값(문자열은 null, 숫자는 0)으로 채워져요.
    public Member() {
    }

    // 매개변수 생성자 — 다섯 가지 정보를 한 번에 받아 객체를 완성해요.
    // this.username 의 this 는 "지금 만들어지는 바로 이 객체" 를 가리켜요.
    public Member(String username, int followers, int posts, int mutualFriends, int daysActive) {
        this.username = username;
        this.followers = followers;
        this.posts = posts;
        this.mutualFriends = mutualFriends;
        this.daysActive = daysActive;
    }

    // 추천 점수 — 이제 인자 없이 자기 자신(this)의 필드를 직접 써요 (지난 시간엔 MemberDemo 밖에 있었음)
    public int calculateRecommendScore() {
        int score = 0;
        score = score + this.followers / 100;     // 팔로워 100명당 1점
        score = score + this.posts / 5;           // 게시물 5개당 1점
        score = score + this.mutualFriends * 10;  // 함께 아는 친구 1명당 10점
        score = score + this.daysActive / 30;     // 활동 30일당 1점
        return score;
    }

    // 등급 — 같은 객체의 calculateRecommendScore() 를 다시 불러서 판정해요
    public String grade() {
        int score = calculateRecommendScore();
        if (score >= 300) {
            return "강력 추천";
        } else if (score >= 150) {
            return "추천";
        } else if (score >= 70) {
            return "보통";
        } else {
            return "관심 낮음";
        }
    }

    // ===== 캡슐화: private 필드를 읽는(getter) 통로 =====
    public String getUsername() {
        return username;
    }

    public int getFollowers() {
        return followers;
    }

    public int getPosts() {
        return posts;
    }

    public int getMutualFriends() {
        return mutualFriends;
    }

    public int getDaysActive() {
        return daysActive;
    }

    // setter — 값을 넣기 전에 검사할 수 있어요. 팔로워는 음수가 될 수 없으니 막아요.
    public void setFollowers(int followers) {
        if (followers < 0) {
            System.out.println("팔로워 수는 음수가 될 수 없어요. 0으로 설정해요.");
            this.followers = 0;
            return;
        }
        this.followers = followers;
    }
}
