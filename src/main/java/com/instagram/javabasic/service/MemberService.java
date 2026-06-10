package com.instagram.javabasic.service;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.DuplicateEmailException;
import com.instagram.javabasic.repository.MemberRepository;

// com/instagram/javabasic/service/MemberService.java
// 회원과 관련된 "할 일(비즈니스 로직)" 을 맡는 계층이에요.
// 저장소는 "넣고 꺼내기" 만 하고, 가입 규칙(이메일이 비었는지·겹치는지) 같은 판단은 여기서 해요.
// 저장소 객체는 직접 만들지 않고 생성자로 외부에서 전달받아요 — 그래야 나중에 다른 저장소로 바꿔 끼우기 쉬워요.
public class MemberService {

    private final MemberRepository memberRepository;

    // 생성자로 저장소를 전달받아요. 서비스는 "어떤 저장소를 쓸지" 를 스스로 정하지 않아요.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입 — 이메일을 검사하고, 이미 쓰는 이메일이면 막은 뒤, 통과하면 저장하고 새 id 를 돌려줘요.
    public Long signup(String username, String email) {
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("이메일은 비어 있을 수 없어요.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("이메일 형식이 올바르지 않아요: " + email);
        }
        if (memberRepository.existsByEmail(email)) {
            throw new DuplicateEmailException("이미 가입된 이메일이에요: " + email);
        }
        Member member = new Member(username, email);
        return memberRepository.save(member);
    }

    // id 로 회원 한 명을 찾아요 — 저장소에 그대로 맡겨요(없으면 저장소가 MemberNotFoundException 을 던져요).
    public Member getMember(Long id) {
        return memberRepository.findById(id);
    }
}
