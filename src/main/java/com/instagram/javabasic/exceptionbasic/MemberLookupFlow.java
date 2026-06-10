package com.instagram.javabasic.exceptionbasic;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.generic.Repository;

// com/instagram/javabasic/exceptionbasic/MemberLookupFlow.java
// 오늘 배운 "던지고 전파받기" 를 한 흐름으로 종합해요.
// 저장소(Repository)는 없는 id 를 찾으면 IllegalArgumentException 을 던져요(저장소가 만든 약속이에요).
//   - 던지는 쪽 : findMember 는 저장소가 던진 예외를 잡지 않고 그대로 통과시켜요(전파).
//   - 받는 쪽   : greet 가 그 예외를 try-catch 로 받아 친절한 안내 문구로 바꿔줘요.
// 저장소는 생성자로 외부에서 전달받아 필드에 보관해요(필요한 도구를 만들어 넣어 주는 방식).
public class MemberLookupFlow {

    // 회원을 보관하는 저장소예요. 직접 만들지 않고 밖에서 전달받아 보관해요.
    private final Repository<Member> repo;

    // 생성자 — 쓸 저장소를 외부에서 받아 필드에 담아요.
    public MemberLookupFlow(Repository<Member> repo) {
        this.repo = repo;
    }

    // 던지는 쪽 — 저장소에서 회원을 찾아요. 없으면 저장소가 던진 예외를
    // 여기서 잡지 않고 그대로 호출자에게 전파해요.
    public Member findMember(Long id) {
        return repo.findById(id);
    }

    // 받는 쪽 — findMember 를 try-catch 로 감싸 결과를 안내 문구로 바꿔줘요.
    // 찾으면 환영 인사를, 없으면 전파돼 온 예외를 잡아 안내 문구를 돌려줘요.
    public String greet(Long id) {
        try {
            Member member = findMember(id);
            return member.getUsername() + "님 환영합니다!";
        } catch (IllegalArgumentException e) {
            return "회원을 찾을 수 없어요: " + e.getMessage();
        }
    }

    public static void main(String[] args) {
        // 회원 저장소를 만들어 회원 두 명을 넣어요.
        Repository<Member> repo = new Repository<>();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        repo.save(2L, new Member("jaehoon", 1240, 42, 5, 200));

        MemberLookupFlow flow = new MemberLookupFlow(repo);

        // 있는 id — 환영 인사가 나와요.
        System.out.println(flow.greet(1L));
        System.out.println(flow.greet(2L));

        // 없는 id — 저장소가 던진 예외가 findMember 를 거쳐 greet 까지 전파되고,
        // greet 가 잡아 안내 문구로 바꿔 돌려줘요. 프로그램은 멈추지 않아요.
        System.out.println(flow.greet(99L));
    }
}
