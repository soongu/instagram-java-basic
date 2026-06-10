package com.instagram.javabasic.modern;

import com.instagram.javabasic.domain.member.Member;

// com/instagram/javabasic/modern/MemberFilter.java
// 회원 한 명을 받아 "이 조건에 맞나?" 를 true/false 로 답하는 약속이에요.
// 추상 메서드가 isMatch 단 하나뿐이라, 이 인터페이스는 람다로 바로 만들 수 있어요.
// @FunctionalInterface 를 붙이면 "메서드가 둘 이상 들어오면 컴파일 에러" 라는 안전장치가 켜져요.
@FunctionalInterface
public interface MemberFilter {

    // 회원이 조건에 맞으면 true, 아니면 false 를 돌려줘요.
    boolean isMatch(Member member);
}
