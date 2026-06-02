package com.instagram.javabasic.domain.member;

// com/instagram/javabasic/domain/member/MemberProcessMain.java
// 과제 3 — 종류에 따라 다른 권한을 안전하게 통합 처리

public class MemberProcessMain {

    public static void processMembers(Member[] members) {
        for (Member m : members) {

            // 향상된 instanceof 패턴 변수:
            // "AdminMember 맞아?" 확인 + "맞으면 admin 변수로 받기" 를 한 줄에
            if (m instanceof AdminMember admin) {
                // 여기서는 admin 이 확실히 관리자라 자식 전용 메서드를 안전하게 부를 수 있어요
                System.out.println(admin.deletePost("post-1"));
                // 여유 연습 — 관리자 전용 행동 하나 더
                System.out.println(admin.suspendMember("spammer123"));

            } else if (m instanceof PremiumMember premium) {
                // 프리미엄일 때만 광고 차단 여부를 확인
                System.out.println(premium.getUsername()
                        + " 광고 차단? " + premium.isAdProtected());
            }
            // 일반 회원은 두 조건 모두 해당 안 됨 → 아무것도 안 하고 건너뜀
        }
    }

    public static void main(String[] args) {
        Member[] members = {
                new Member("minji", 1000, 0, 0, 0),
                new AdminMember("jaehoon", 1000, 0, 0, 0, "콘텐츠 관리자"),
                new PremiumMember("seungwoo", 1000, 0, 0, 0, true)
        };

        processMembers(members);
    }
}
