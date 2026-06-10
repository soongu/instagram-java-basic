package com.instagram.javabasic.exceptionbasic;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/exceptionbasic/MemberLookupFlowV2.java
// 오늘 배운 것을 모두 모은 종합편이에요. 지난 시간의 MemberLookupFlow 와 비교해보세요.
//   그때 : 저장소의 IllegalArgumentException 이 그대로 greet 까지 전파됐어요.
//   지금 : 중간(findMember)에서 우리 서비스 전용 예외로 재포장하고, 원인은 cause 로 보존해요.
// 층마다 역할이 나뉘어요 — 저장소는 기술적인 예외를, 서비스는 의미가 분명한 예외를,
// 마지막 받는 쪽(greet)은 메시지와 원인을 합쳐 사람이 읽을 안내 문구를 만들어요.
public class MemberLookupFlowV2 {

    // 회원 전용 저장소 — 외부에서 전달받아 보관해요.
    private final MemberRepository repo;

    public MemberLookupFlowV2(MemberRepository repo) {
        this.repo = repo;
    }

    // 재포장하는 쪽 — 저장소의 기본 예외를 잡아 MemberNotFoundException 으로 바꿔 던져요.
    // 원래 예외 e 를 cause 로 함께 담아서, 처음 사고의 흔적이 사라지지 않아요.
    public Member findMember(Long id) {
        try {
            return repo.findById(id);
        } catch (IllegalArgumentException e) {
            throw new MemberNotFoundException("회원을 찾을 수 없어요: id " + id, e);
        }
    }

    // 최종 받는 쪽 — 예외의 겉(getMessage)과 속(getCause)을 둘 다 살려 안내 문구를 만들어요.
    // 사용자에게는 우리 서비스의 말로, 괄호 안에는 저장소가 남긴 처음 기록까지 함께 보여줘요.
    public String greet(Long id) {
        try {
            Member member = findMember(id);
            return member.getUsername() + "님 환영합니다!";
        } catch (MemberNotFoundException e) {
            return e.getMessage() + " (저장소 기록: " + e.getCause().getMessage() + ")";
        }
    }

    public static void main(String[] args) {
        // 회원 전용 저장소를 만들어 회원 두 명을 넣어요.
        MemberRepository repo = new MemberRepository();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        repo.save(2L, new Member("jaehoon", 1240, 42, 5, 200));

        MemberLookupFlowV2 flow = new MemberLookupFlowV2(repo);

        // 있는 id — 환영 인사가 나와요.
        System.out.println(flow.greet(1L));
        System.out.println(flow.greet(2L));

        // 없는 id — 저장소의 예외가 findMember 에서 MemberNotFoundException 으로 재포장되고,
        // greet 가 잡아 메시지와 원인을 합친 안내 문구를 돌려줘요. 프로그램은 멈추지 않아요.
        System.out.println(flow.greet(99L));
    }
}
