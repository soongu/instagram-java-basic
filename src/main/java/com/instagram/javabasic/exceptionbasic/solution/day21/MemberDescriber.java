package com.instagram.javabasic.exceptionbasic.solution.day21;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.generic.Repository;

// com/instagram/javabasic/exceptionbasic/solution/day21/MemberDescriber.java
// 만능 저장소(Repository<Member>)에서 회원을 꺼내 한 줄 소개 문장을 만드는 연습이에요.
// Repository.findById 는 없는 id 를 찾으면 IllegalArgumentException 을 던져요.
// 그래서 try-catch 로 두 가지 상황을 나눠서 받아내요.
//   1) IllegalArgumentException — "그 번호의 회원이 없다" 는 구체적인 상황
//   2) RuntimeException        — 그 밖에 예상 못한 실행 중 오류를 모두 받는 더 넓은 그물
// 핵심 규칙: catch 는 "구체적인 것 → 일반적인 것" 순서로 적어야 해요.
//   RuntimeException 은 IllegalArgumentException 의 부모(더 넓은 종류)라서,
//   넓은 것을 먼저 적으면 구체적인 catch 가 영영 실행될 수 없어요(자바가 컴파일에서 막아줘요).
public class MemberDescriber {

    // 회원 소개 만들기 — 있는 id 면 "회원: 이름", 없으면 안내 문구를 돌려줘요.
    // try 안에서 repo.findById(id) 로 회원을 꺼내 username 으로 문장을 만들어요.
    //   - 없는 id 면 findById 가 IllegalArgumentException 을 던지고, 첫 catch 가 받아요.
    //   - 그 밖의 실행 중 오류는 두 번째(더 넓은) catch 가 받아요.
    public String describe(Repository<Member> repo, Long id) {
        try {
            Member member = repo.findById(id);
            return "회원: " + member.getUsername();
        } catch (IllegalArgumentException e) {
            return "없는 회원이에요.";
        } catch (RuntimeException e) {
            return "알 수 없는 오류예요.";
        }
    }

    public static void main(String[] args) {
        Repository<Member> repo = new Repository<>();
        repo.save(1L, new Member("minji", 8500, 150, 12, 400));
        repo.save(2L, new Member("jaehoon", 1240, 42, 5, 90));

        MemberDescriber describer = new MemberDescriber();

        // 있는 id — 이름을 담은 문장이 나와요.
        System.out.println(describer.describe(repo, 1L));
        System.out.println(describer.describe(repo, 2L));

        // 없는 id — 예외가 잡혀서 안내 문구가 나오고, 프로그램은 멈추지 않아요.
        System.out.println(describer.describe(repo, 999L));

        System.out.println("끝까지 잘 실행됐어요!");
    }
}
