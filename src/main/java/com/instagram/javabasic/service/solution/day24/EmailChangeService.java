package com.instagram.javabasic.service.solution.day24;

import com.instagram.javabasic.domain.member.Member;
import com.instagram.javabasic.exceptionbasic.DuplicateEmailException;
import com.instagram.javabasic.repository.MemberRepository;

// com/instagram/javabasic/service/solution/day24/EmailChangeService.java
// [과제 2 예시답안] 회원의 이메일을 바꾸는 서비스예요.
// 한 메서드 안에서 두 예외가 협력해요 — 회원이 없으면 저장소가 MemberNotFoundException 을 자동으로 던지고,
// 새 이메일이 다른 회원 것이면 서비스가 DuplicateEmailException 을 직접 던져요.
public class EmailChangeService {

    private final MemberRepository memberRepository;

    public EmailChangeService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 이메일 변경 — 회원을 찾고(없으면 자동 예외), 형식·중복을 검사한 뒤 바꿔요.
    public void changeEmail(Long id, String newEmail) {
        Member member = memberRepository.findById(id);
        if (newEmail == null || !newEmail.contains("@")) {
            throw new IllegalArgumentException("이메일 형식이 올바르지 않아요: " + newEmail);
        }
        // 자기가 이미 쓰는 이메일은 그대로 둬도 되니 검사에서 빼요 — "다른 회원" 이 쓸 때만 막아요.
        if (!newEmail.equals(member.getEmail()) && memberRepository.existsByEmail(newEmail)) {
            throw new DuplicateEmailException("이미 가입된 이메일이에요: " + newEmail);
        }
        member.setEmail(newEmail);
    }
}
