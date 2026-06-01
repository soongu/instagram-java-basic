package com.instagram.javabasic.domain.member;

// com/instagram/javabasic/domain/member/AdminMember.java
// 관리자 회원 — 일반 회원(Member)이 가진 모든 것을 그대로 물려받고(extends),
// 거기에 관리자만의 정보(역할)와 행동(게시물 삭제)을 더해요.
// "관리자도 회원이다" 라는 관계를 상속으로 표현해요. (AdminMember 는 Member 의 한 종류)
public class AdminMember extends Member {

    // 관리자만 추가로 갖는 정보 — 예: "콘텐츠 관리자"
    private String adminRole;

    // 생성자 — 첫 줄에서 super(...) 로 부모(Member)의 생성자를 먼저 불러
    // 물려받은 다섯 필드를 채우고, 그 다음 관리자만의 필드를 채워요.
    public AdminMember(String username, int followers, int posts, int mutualFriends, int daysActive, String adminRole) {
        super(username, followers, posts, mutualFriends, daysActive);
        this.adminRole = adminRole;
    }

    public String getAdminRole() {
        return adminRole;
    }

    // 관리자만의 행동 — username 은 부모의 private 필드라 직접 못 쓰고,
    // 부모가 열어둔 public getter(getUsername())로 읽어요.
    public String deletePost(String postId) {
        return "[관리자] " + getUsername() + " 가 게시물 " + postId + " 을(를) 삭제했어요.";
    }

    // 오버라이딩 — 부모의 점수 계산을 super 로 그대로 쓰고, 관리자 보너스 50점을 더해요.
    @Override
    public int calculateRecommendScore() {
        return super.calculateRecommendScore() + 50;
    }

    // 오버라이딩 — 부모의 toString 결과를 super 로 가져와 뒤에 역할 표시를 붙여요.
    @Override
    public String toString() {
        return super.toString() + " [관리자: " + adminRole + "]";
    }
}
