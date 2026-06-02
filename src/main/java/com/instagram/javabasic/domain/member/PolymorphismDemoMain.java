package com.instagram.javabasic.domain.member;

// com/instagram/javabasic/domain/member/PolymorphismDemoMain.java
// 과제 1 — 부모 타입 배열에 자식을 섞어 담고 점수가 갈리는지 확인

public class PolymorphismDemoMain {

    public static void main(String[] args) {

        // 같은 스탯(팔로워 1000명 등)으로 세 회원을 만들어 비교해요.
        // 기본 점수는 같지만, 보너스가 달라 최종 점수가 갈리는 걸 보려고요.
        Member normal  = new Member("minji", 1000, 0, 0, 0);
        Member admin   = new AdminMember("jaehoon", 1000, 0, 0, 0, "콘텐츠 관리자");
        Member premium = new PremiumMember("seungwoo", 1000, 0, 0, 0, true);

        // 셋을 Member 타입 배열 하나에 담아요 — 자식을 부모 자리에 담는 게 업캐스팅
        Member[] members = { normal, admin, premium };

        // 향상된 for 로 순회하며 같은 메서드를 불러요.
        // 호출은 똑같은데, 실제 객체 타입의 버전이 불려 점수가 갈려요 (동적 디스패치)
        for (Member m : members) {
            System.out.println(m.getUsername() + " 추천 점수: " + m.calculateRecommendScore());
        }
    }
}
