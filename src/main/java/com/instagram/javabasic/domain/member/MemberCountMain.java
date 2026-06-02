package com.instagram.javabasic.domain.member;

// com/instagram/javabasic/domain/member/MemberCountMain.java
// 과제 2 — 배열을 훑으며 회원 종류별로 몇 명인지 센다

public class MemberCountMain {

    // Member 배열을 받아 종류별 인원을 세고 출력하는 메서드
    public static void countByType(Member[] members) {
        int adminCount   = 0;
        int premiumCount = 0;
        int normalCount  = 0;

        for (Member m : members) {
            // ★ 좁은 타입(자식)부터 먼저 검사한다.
            //   AdminMember 도 결국 Member 라서, Member 부터 물으면 전부 일반으로 빠져버려요.
            if (m instanceof AdminMember) {
                adminCount++;
            } else if (m instanceof PremiumMember) {
                premiumCount++;
            } else {
                // 관리자도 프리미엄도 아니면 일반 회원
                normalCount++;
            }
        }

        System.out.println("관리자: " + adminCount + "명");
        System.out.println("프리미엄: " + premiumCount + "명");
        System.out.println("일반: " + normalCount + "명");
    }

    public static void main(String[] args) {
        Member[] members = {
                new Member("minji", 1000, 0, 0, 0),
                new AdminMember("jaehoon", 1000, 0, 0, 0, "콘텐츠 관리자"),
                new PremiumMember("seungwoo", 1000, 0, 0, 0, true),
                new AdminMember("hyein", 500, 0, 0, 0, "신고 처리"),
                new Member("doyoon", 200, 0, 0, 0)
        };

        countByType(members);
    }
}
