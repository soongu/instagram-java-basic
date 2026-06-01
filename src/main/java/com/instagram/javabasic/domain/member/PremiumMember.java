package com.instagram.javabasic.domain.member;

// com/instagram/javabasic/domain/member/PremiumMember.java
// 프리미엄 회원 — 일반 회원(Member)이 가진 모든 것을 그대로 물려받고(extends),
// 거기에 프리미엄만의 정보(광고 차단 여부)와 혜택(추천 점수 보너스)을 더해요.
// "프리미엄 회원도 회원이다" 라는 관계를 상속으로 표현해요.
public class PremiumMember extends Member {

    // 프리미엄만 추가로 갖는 정보 — 광고를 차단할지 여부
    private boolean adProtected;

    // 생성자 — 첫 줄 super(...) 로 부모(Member)의 다섯 필드를 먼저 채우고,
    // 그 다음 프리미엄만의 필드를 채워요.
    public PremiumMember(String username, int followers, int posts, int mutualFriends, int daysActive, boolean adProtected) {
        super(username, followers, posts, mutualFriends, daysActive);
        this.adProtected = adProtected;
    }

    public boolean isAdProtected() {
        return adProtected;
    }

    // 오버라이딩 — 부모의 점수 계산을 super 로 그대로 쓰고, 프리미엄 보너스 30점을 더해요.
    @Override
    public int calculateRecommendScore() {
        return super.calculateRecommendScore() + 30;
    }

    // 오버라이딩 — 부모의 toString 결과를 super 로 가져와 뒤에 프리미엄 표시를 붙여요.
    @Override
    public String toString() {
        return super.toString() + " [프리미엄]";
    }
}
