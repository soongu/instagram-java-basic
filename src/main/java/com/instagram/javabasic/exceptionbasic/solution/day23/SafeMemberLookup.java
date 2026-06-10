package com.instagram.javabasic.exceptionbasic.solution.day23;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.MemberNotFoundException;
import com.instagram.javabasic.exceptionbasic.MemberRepository;

// com/instagram/javabasic/exceptionbasic/solution/day23/SafeMemberLookup.java
// [중] 과제 2 — 저장소가 던진 표준 예외를 우리 서비스 예외로 재포장하되, 원인을 보존해요.
// 저장소(MemberRepository)는 없는 id 에 IllegalArgumentException 을 던져요.
// 그걸 잡아 MemberNotFoundException 으로 바꿔 던지면서, 두 번째 인자로 원래 예외 e 를
// 함께 넘겨 cause 로 보존하는 게 핵심이에요(예외 체이닝).
public class SafeMemberLookup {

    // 회원 저장소 — 외부에서 전달받아 보관해요.
    private final MemberRepository repo;

    public SafeMemberLookup(MemberRepository repo) {
        this.repo = repo;
    }

    // 안전 조회 — 저장소의 표준 예외를 잡아 우리 서비스 예외로 재포장해 던져요.
    // 두 번째 인자 e 를 빼먹으면 getCause() 가 null 이 되니 꼭 함께 넘겨요.
    public Member getMemberSafely(Long id) {
        try {
            return repo.findById(id);
        } catch (IllegalArgumentException e) {
            throw new MemberNotFoundException("회원 id " + id + " 를 찾을 수 없어요", e);
        }
    }

    public static void main(String[] args) {
        MemberRepository repo = new MemberRepository();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));

        SafeMemberLookup lookup = new SafeMemberLookup(repo);

        // 있는 id — 회원이 그대로 나와요.
        System.out.println("조회 성공: " + lookup.getMemberSafely(1L).getUsername());

        // 없는 id — 재포장된 예외를 잡아 겉(메시지)과 속(원인)을 둘 다 들여다봐요.
        try {
            lookup.getMemberSafely(99L);
        } catch (MemberNotFoundException e) {
            System.out.println("잡은 예외   : " + e.getMessage());
            System.out.println("원인(cause) : " + e.getCause().getMessage());
        }
    }
}
