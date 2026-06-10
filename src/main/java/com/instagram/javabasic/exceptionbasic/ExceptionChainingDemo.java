package com.instagram.javabasic.exceptionbasic;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.generic.Repository;

// com/instagram/javabasic/exceptionbasic/ExceptionChainingDemo.java
// 예외 체이닝(연결) — 잡은 예외를 더 의미 있는 예외로 "재포장" 하되, 원래 예외를 버리지 않고
// cause(원인) 자리에 담아 넘기는 기술이에요.
//   저장소가 던진 IllegalArgumentException → 잡아서 → MemberNotFoundException(메시지, 원인) 으로 다시 던져요.
// 잡는 쪽은 의미가 분명한 예외 이름으로 상황을 알고, getCause() 로 처음 사고까지 추적할 수 있어요.
public class ExceptionChainingDemo {

    // 회원 저장소 — 지난 시간처럼 외부에서 전달받아 보관해요.
    private final Repository<Member> repo;

    public ExceptionChainingDemo(Repository<Member> repo) {
        this.repo = repo;
    }

    // 재포장하는 쪽 — 저장소의 기술적인 예외(IllegalArgumentException)를 잡아서,
    // 우리 서비스의 언어로 말하는 예외(MemberNotFoundException)로 바꿔 던져요.
    // 두 번째 인자 e 가 핵심이에요. 원래 예외를 cause 로 담아 흔적을 보존해요.
    public Member findMember(Long id) {
        try {
            return repo.findById(id);
        } catch (IllegalArgumentException e) {
            throw new MemberNotFoundException("회원을 찾을 수 없어요: id " + id, e);
        }
    }

    public static void main(String[] args) {
        Repository<Member> repo = new Repository<>();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));

        ExceptionChainingDemo demo = new ExceptionChainingDemo(repo);

        // 있는 id — 평소처럼 회원이 나와요.
        System.out.println("조회 성공: " + demo.findMember(1L).getUsername());

        // 없는 id — 재포장된 예외를 잡아서, 겉(메시지)과 속(원인)을 둘 다 들여다봐요.
        try {
            demo.findMember(99L);
        } catch (MemberNotFoundException e) {
            System.out.println("잡은 예외   : " + e.getMessage());
            System.out.println("원인(cause) : " + e.getCause());
        }
    }
}
